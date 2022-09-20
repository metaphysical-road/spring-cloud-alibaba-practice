package com.alibaba.cloud.youxia.config;

import com.alibaba.cloud.youxia.dto.SkywalkingAlarmMessage;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Configuration
@ConfigurationProperties(prefix = "skywalking")
@Slf4j
public class SkwalkingWhiteListConfig {
    private List<Map<String, String>> skywalkingWhiteLists;
    public boolean isWhiteList(SkywalkingAlarmMessage alarmMessage){
        //alarmMessage  - map
        Map<String,Object> alarmMessageMap = JSONObject.parseObject(JSONObject.toJSONString(alarmMessage), Map.class);
        JSONObject logJson = (JSONObject) alarmMessageMap.get("logsMap");
        Map<String,Object> logMap = JSONObject.parseObject(logJson.toJSONString(), Map.class);
        alarmMessageMap.remove("logsMap");
        alarmMessageMap.putAll(logMap);

        JSONObject tagsJson = (JSONObject) alarmMessageMap.get("tagsMap");
        Map<String,Object> tagMap = JSONObject.parseObject(tagsJson.toJSONString(), Map.class);
        alarmMessageMap.remove("tagsMap");
        alarmMessageMap.putAll(tagMap);
        for (Map<String, String> skywalkingWhiteMap : skywalkingWhiteLists){
            if (equals(alarmMessageMap,skywalkingWhiteMap)){
                log.info("skywalking whiteList filter{}", alarmMessage);
                return true;
            }
        }
        return false;
    }

    public boolean equals(Map<String,Object> alarmMessage,Map<String,String> whiteList){
        Set<Map.Entry<String, String>> entries = whiteList.entrySet();
        for(Map.Entry<String, String> entry: entries){
            if(!alarmMessage.containsKey(entry.getKey()) || !alarmMessage.get(entry.getKey()).equals(entry.getValue())){
                return false;
            }
        }
        return true;
    }
}
