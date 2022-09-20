package com.alibaba.cloud.youxia.job.nacos;

import com.alibaba.cloud.youxia.config.AlarmConfig;
import com.alibaba.cloud.youxia.config.AlarmConfigService;
import com.alibaba.cloud.youxia.config.AlarmServicePhoneConfig;
import com.alibaba.cloud.youxia.config.NacosAlarmConfig;
import com.alibaba.cloud.youxia.dto.NacosAlarmMessage;
import com.alibaba.cloud.youxia.service.NacosAlarmService;
import com.alibaba.cloud.youxia.util.DingDingUtils;
import com.alibaba.cloud.youxia.util.HttpClientUtils;
import com.alibaba.cloud.youxia.util.RestResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.HttpStatus;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RefreshScope
@Slf4j
public class NacosHealthAlarmPullJob implements SimpleJob {
    @Autowired
    NacosAlarmConfig nacosAlarmConfig;
    @Resource
    private AlarmConfig alarmConfig;
    @Autowired
    AlarmConfigService configService;
    @Autowired
    NacosAlarmService nacosAlarmService;
    @Autowired
    AlarmServicePhoneConfig alarmServicePhoneConfig;

    private static final String INSTANCE_NULL = "该服务下无可用实例";
    private static final String INSTANCE_BAD = "该实例不可用";
    private static final String SERVICE_BAD = "该服务不可用";
    private List<String> newServices = new ArrayList<>();
    private Map<String, List<String>> newInstanceMap = new HashMap<>();

    @Override
    public void execute(ShardingContext shardingContext) {
        if (!NacosAlarmService.ISINIT) {
            return;
        }
        List<NacosAlarmMessage> alarmMessageList = new ArrayList<>();
        compareService();
        List<String> missService = new ArrayList<>();
        try {
            if (!newServices.containsAll(NacosAlarmService.serverNameList)) {
                for (String serviceName : NacosAlarmService.serverNameList) {
                    if (!newServices.contains(serviceName)) {
                        StringBuilder instanceNameBuilder = new StringBuilder();
                        for (String instanceName :NacosAlarmService.instanceMap.get(serviceName)){
                            instanceNameBuilder.append(instanceName).append(",");
                        }
                        NacosAlarmMessage nacosAlarmMessage = new NacosAlarmMessage(
                            nacosAlarmConfig.getNacosNameSpaceId(), serviceName, instanceNameBuilder.length() > 0 ?
                                instanceNameBuilder.substring(0,instanceNameBuilder.length()-1) : "", SERVICE_BAD, new Date());
                        alarmMessageList.add(nacosAlarmMessage);
                        missService.add(serviceName);
                        log.info("service miss,{}", nacosAlarmMessage.toString());
                    }
                }
            }
            for (String serviceName : NacosAlarmService.serverNameList) {
                if (missService.contains(serviceName)) {
                    continue;
                }
                JSONArray ips = getInstanceAlarm(serviceName);
                if (ips.isEmpty()) {
                    StringBuilder  instanceNameBuilder= new StringBuilder();
                    for (String instanceName :NacosAlarmService.instanceMap.get(serviceName)){
                        instanceNameBuilder.append(instanceName).append(",");
                    }
                    NacosAlarmMessage nacosAlarmMessage = new NacosAlarmMessage(nacosAlarmConfig.getNacosNameSpaceId(),
                        serviceName, instanceNameBuilder.length() > 0 ? instanceNameBuilder.substring(0,instanceNameBuilder.length()-1) : "",
                            INSTANCE_NULL, new Date());
                    alarmMessageList.add(nacosAlarmMessage);
                    log.info("Instance isEmpty,{}", nacosAlarmMessage.toString());
                } else {
                    for (Object ip : ips) {
                        if (((String) ip).contains("false")) {
                            NacosAlarmMessage nacosAlarmMessage = new NacosAlarmMessage(
                                nacosAlarmConfig.getNacosNameSpaceId(), serviceName, (String) ip, INSTANCE_BAD,
                                new Date());
                            alarmMessageList.add(nacosAlarmMessage);
                            log.info("Instance health false,{}", nacosAlarmMessage.toString());
                        }
                    }
                    List<String> instances = NacosAlarmService.instanceMap.get(serviceName);
                    String[] ipList = ips.toArray(new String[0]);
                    List<String> ipSimpleList = new ArrayList<>();
                    for (String ip : ipList) {
                        String ipSimple = ip.split(":")[0];
                        ipSimpleList.add(ipSimple);
                    }
                    for (String instance : instances) {
                        if (!ipSimpleList.contains(instance)) {
                            NacosAlarmMessage nacosAlarmMessage = new NacosAlarmMessage(
                                nacosAlarmConfig.getNacosNameSpaceId(), serviceName, instance, INSTANCE_BAD,
                                new Date());
                            alarmMessageList.add(nacosAlarmMessage);
                            log.info("Instance miss,{}", nacosAlarmMessage.toString());
                        }
                    }
                }
            }
            removeDuplicatedAlarm(alarmMessageList);
            sendAlarm(alarmMessageList);
        } catch (Exception e) {
            log.error("Nacos 告警失败", e);
        }
    }

    List<NacosAlarmMessage> cacheAlarmMessageList = new ArrayList<>();

    private void removeDuplicatedAlarm(List<NacosAlarmMessage> alarmMessageList) {
        Iterator<NacosAlarmMessage> alarmMessageIterator = alarmMessageList.iterator();
        while (alarmMessageIterator.hasNext()) {
            NacosAlarmMessage alarmMessge = alarmMessageIterator.next();
            int num = cacheAlarmMessageList.indexOf(alarmMessge);
            if (num != -1) {
                if (alarmMessge.getTime().getTime() - cacheAlarmMessageList.get(num).getTime().getTime() < 1000 * 30) {
                    alarmMessageIterator.remove();
                    log.info("nacos告警发送太频繁： {}",alarmMessge.toString());
                } else {
                    cacheAlarmMessageList.set(num, alarmMessge);
                }
            } else {
                cacheAlarmMessageList.add(alarmMessge);
            }
        }
    }

    private JSONArray getInstanceAlarm(String serviceName) throws Exception {
        Map<String, String> map = new HashMap<>(2);
        map.put("key", nacosAlarmConfig.getNacosNameSpaceId() + "##DEFAULT_GROUP@@" + serviceName);
        RestResult restResult = HttpClientUtils
            .get(nacosAlarmConfig.getNacosIp() + nacosAlarmConfig.getNacosInstanceAlarmApi(), map);

        if (restResult.getCode() != HttpStatus.SC_OK) {
            throw new Exception("HTTP请求失败" + restResult.getCode() + " message: " + restResult.getMessage());
        }
        String message = restResult.getMessage();
        JSONObject jsonObject = JSON.parseObject(message);
        return jsonObject.getJSONArray("ips");
    }

    public void sendAlarm(List<NacosAlarmMessage> alarmMessageList) throws ApiException {
        if (alarmMessageList.isEmpty()) {
            return;
        }
        OapiRobotSendRequest markDownRequest = new OapiRobotSendRequest();
        markDownRequest.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("NACOS告警");
        StringBuffer markDownText = new StringBuffer();
        AtomicInteger successCount = new AtomicInteger();

        alarmMessageList.forEach(info -> {
            String addText =
                "### NACOS报警 \n\n" + "### 所属平台 :" + nacosAlarmConfig.getPlatform() + "\n\n" + "### 命名空间 :" + info
                    .getNameSpace() + "\n\n" + "### 所属服务 :" + info.getServiceName() + "\n\n" + "### 服务实例 :" + info
                    .getIp() + "\n\n" + "### 告警时间 :" + DateFormatUtils.format(info.getTime(), "yyyy-MM-dd HH:mm:ss")
                    + "\n\n" + "### 告警内容 :" + info.getMessage() + "\n\n" + "---" + "\n\n";

            markDownText.append(addText);
            successCount.getAndIncrement();
        });
        markdown.setText(String.valueOf(markDownText));
        markDownRequest.setMarkdown(markdown);
        String secret = alarmConfig.getSecret();
        String webHook = alarmConfig.getWebhook();
        DingTalkClient client = DingDingUtils.getClient(secret,webHook);
        OapiRobotSendResponse response = client.execute(markDownRequest);
        log.info("execute:{}" + response.toString());
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("请相关人员尽快处理报警所示异常");
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        addResponsible(alarmMessageList, at);
        request.setAt(at);
        client.execute(request);
    }

    private void addResponsible(List<NacosAlarmMessage> alarmMessageList, OapiRobotSendRequest.At at) {
        List<String> atMobiles = new ArrayList<>();
        alarmMessageList.forEach(info -> {
            atMobiles.add(alarmServicePhoneConfig.getPhoneNumber().get(info.getServiceName()));
        });
        if (atMobiles.contains(null)) {
            at.setIsAtAll("true");
        } else {
            at.setAtMobiles(atMobiles);
        }
    }

    public void compareService() {
        newServices = nacosAlarmService.selectServices();
        List<String> oldServices = NacosAlarmService.serverNameList;

        Map<String, List<String>> oldInstanceMap = NacosAlarmService.instanceMap;
        for (String serviceName : newServices) {
            if (!oldServices.contains(serviceName)) {
                oldServices.add(serviceName);
                List<String> instances = nacosAlarmService.selectInstances(serviceName);
                oldInstanceMap.put(serviceName, instances);
                log.info("新增服务：{}", serviceName);
                newInstanceMap.put(serviceName, instances);
            } else {
                List<String> oldInstances = oldInstanceMap.get(serviceName);
                List<String> newInstances = nacosAlarmService.selectInstances(serviceName);
                if (!oldInstances.containsAll(newInstances)) {
                    for (String instance : newInstances) {
                        if (!oldInstances.contains(instance)) {
                            oldInstances.add(instance);
                            log.info("新增实例 服务：{},实例：{}", serviceName, instance);
                        }
                    }
                }
                newInstanceMap.put(serviceName, newInstances);
            }
        }
    }
}
