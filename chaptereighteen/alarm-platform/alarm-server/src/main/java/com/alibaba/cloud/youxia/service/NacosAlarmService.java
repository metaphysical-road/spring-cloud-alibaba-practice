package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.config.NacosAlarmConfig;
import com.alibaba.cloud.youxia.util.HttpClientUtils;
import com.alibaba.cloud.youxia.util.RestResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class NacosAlarmService {
    public static List<String> serverNameList = new ArrayList<>();
    public static Map<String, List<String>> instanceMap = new ConcurrentHashMap<>();
    public static volatile boolean ISINIT = false;

    @Autowired
    NacosAlarmConfig nacosAlarmConfig;

    @PostConstruct
    public void getInitNacosConfig() {
        log.info("nacos告警开始初始化");
        serverNameList = selectServices();
        log.info("更新服务缓存，{}", serverNameList);
        for (String serviceName : serverNameList) {
            List<String> instancesList = selectInstances(serviceName);
            instanceMap.put(serviceName, instancesList);
            log.info("更新服务下的实例，服务名{}，实例{}", serviceName, instancesList);
        }
        ISINIT = true;
    }

    public List<String> selectServices() {
        List<String> serverName = new ArrayList<>();
        String nameSpaceId = nacosAlarmConfig.getNacosNameSpaceId();
        Map<String, String> params = new HashMap<>(4);
        params.put("namespaceId", nameSpaceId);
        params.put("pageNo", "1");
        params.put("pageSize", String.valueOf(Integer.MAX_VALUE));
        try {
            JSONObject serviceListFirst = getServiceList(params);
            JSONArray doms = serviceListFirst.getJSONArray("doms");
            Iterator<Object> iterator = doms.iterator();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                serverName.add((String) next);
            }
        } catch (Exception e) {
            log.error("获取nacos端服务列表失败。", e);
        }
        return serverName;
    }

    public List<String> selectInstances(String serviceName) {
        JSONObject serviceInstanceList = null;
        try {
            serviceInstanceList = getServiceInstanceList(serviceName);
            if (serviceInstanceList != null) {
                return handleInstance(serviceInstanceList);
            }
        } catch (Exception e) {
            log.error("获取服务对应的实例失败，{}", serviceName, e);
        }
        return null;
    }

    private JSONObject getServiceList(Map<String, String> params) throws Exception {
        RestResult restResult = HttpClientUtils.get(nacosAlarmConfig.getNacosIp() + nacosAlarmConfig.getNacosServiceListApi(), params);
        if (restResult.getCode() != HttpStatus.SC_OK) {
            throw new Exception("HTTP请求失败" + restResult.getCode() + " message: " + restResult.getMessage());
        }
        String message = restResult.getMessage();
        return JSON.parseObject(message);
    }

    private JSONObject getServiceInstanceList(String serviceName) throws Exception {
        Map<String, String> params = new HashMap<>(3);
        params.put("namespaceId", nacosAlarmConfig.getNacosNameSpaceId());
        params.put("serviceName", serviceName);
        RestResult restResult = HttpClientUtils
            .get(nacosAlarmConfig.getNacosIp() + nacosAlarmConfig.getNacosInstanceListApi(), params);
        if (restResult.getCode() != HttpStatus.SC_OK) {
            throw new Exception("HTTP请求失败" + restResult.getCode() + " message: " + restResult.getMessage());
        }
        String message = restResult.getMessage();
        return JSON.parseObject(message);
    }

    private List<String> handleInstance(JSONObject serviceInstanceList) {
        JSONArray hosts = serviceInstanceList.getJSONArray("hosts");
        Iterator<Object> iterator = hosts.iterator();
        List<String> instanceList = new ArrayList<>();
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            String ip = next.getString("ip");
            instanceList.add(ip);
        }
        return instanceList;
    }
}
