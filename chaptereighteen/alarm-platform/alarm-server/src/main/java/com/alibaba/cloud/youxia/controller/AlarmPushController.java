package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.config.AlarmConfig;
import com.alibaba.cloud.youxia.config.AlarmConfigService;
import com.alibaba.cloud.youxia.dto.AlarmMessageDTO;
import com.alibaba.cloud.youxia.util.AlarmRedisUtil;
import com.alibaba.cloud.youxia.util.DingDingUtils;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(value = "/alarm")
@Slf4j
public class AlarmPushController {
    @Autowired
    private AlarmConfigService alarmConfigService;
    @Resource
    private AlarmConfig alarmConfig;
    @Resource
    private AlarmRedisUtil alarmRedisUtil;

    @LoadBalanced
    @RequestMapping(value = "/alarmPushData", method = RequestMethod.POST)
    public void pushAlarmDingDingMessage(@RequestBody List<AlarmMessageDTO> alarmMessageList, HttpServletRequest httpServletRequest) {
        log.info("alarmMessage:{}", alarmMessageList.toString());
        if (!alarmConfig.getGlobalSwitch()) {
            return;
        }
        //获取发出请求的机器IP
        String remoteAddr = httpServletRequest.getRemoteAddr();
        log.info("remoteAddr={}", remoteAddr);
        //获取IP所属平台
        String platform = alarmConfigService.getPlatformByIp(remoteAddr);
        if (platform.length() == 0) {
            log.info("no config in nacos, remoteAddr={}", remoteAddr);
            return;
        }
        try {
            //组成告警markdown消息
            OapiRobotSendRequest markDownRequest = new OapiRobotSendRequest();
            markDownRequest.setMsgtype("markdown");
            OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
            markdown.setTitle("SkyWalking告警");
            StringBuffer markDownText = new StringBuffer();
            AtomicInteger successCount = new AtomicInteger();
            List<String> insertIdList = new ArrayList<>();
            List<String> atList = new ArrayList<>();
            alarmMessageList.forEach(info -> {
                //校验白名单
                if (alarmConfigService.isWhiteService(info.getName())) {
                    return;
                }
                String addText =
                        "### 所属平台 :" + platform + "\n\n" + "### 告警序号 :" + info.getId0() + "\n\n" + "告警涉及服务 :" + info
                                .getName() + "\n\n" + "告警类型 :" + info.getScope() + "\n\n" + "告警规则 :" + info.getRuleName()
                                + "\n\n" + "告警时间 :" + DateFormatUtils.format(info.getStartTime(), "yyyy-MM-dd hh:MM:ss")
                                + "\n\n" + "告警内容 :" + info.getAlarmMessage() + "\n\n" + "---" + "\n\n";
                if (insertIdList.contains(info.getId0())) {
                    return;
                }
                if (alarmRedisUtil.isExistAlarmId(info.getId0())) {
                    return;
                } else {
                    alarmRedisUtil.updateAlarmId(info.getId0());
                }
                String principalNum = alarmConfigService.getServicePrincipalNum(info.getName());
                if (principalNum != null) {
                    atList.add(principalNum);
                }
                markDownText.append(addText);
                successCount.getAndIncrement();
                insertIdList.add(info.getId0());
            });
            //若markdown信息为null 则不发信息
            if (insertIdList.size() < 1) {
                return;
            }
            markDownText.append("[skyWalking地址](").append(alarmConfig.getSkyWalkingUrl()).append(")");
            markdown.setText(String.valueOf(markDownText));
            markDownRequest.setMarkdown(markdown);
            String secret = alarmConfig.getSecret();
            String webHook = alarmConfig.getWebhook();
            DingTalkClient client = DingDingUtils.getClient(secret,webHook);
            OapiRobotSendResponse response = client.execute(markDownRequest);
            log.info("execute:{}" + response.toString());
            //组成负责人信息
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype("text");
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent("请相关人员尽快处理告警所示异常");
            request.setText(text);
            if (CollectionUtils.isNotEmpty(atList)) {
                OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
                at.setAtMobiles(atList);
                request.setAt(at);
            }
            OapiRobotSendResponse testResponse = client.execute(request);
            log.info("execute:{}" + testResponse.toString());
        } catch (Exception e) {
            log.error("alarmError", e);
        }
    }
}
