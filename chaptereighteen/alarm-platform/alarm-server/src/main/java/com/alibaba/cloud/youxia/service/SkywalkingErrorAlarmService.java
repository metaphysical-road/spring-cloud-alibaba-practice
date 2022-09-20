package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.dto.SkywalkingAlarmMessage;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SkywalkingErrorAlarmService {

    public void handleMessage(List<SkywalkingAlarmMessage> skywalkingAlarmMessageList, String message) {
        JSONObject jsonObject = JSONObject.parseObject(message);
        JSONObject data = (JSONObject) jsonObject.get("data");
        JSONObject trace = null;
        JSONArray spans = new JSONArray();
        if (data != null) {
            trace = (JSONObject) data.get("trace");
        }
        if (trace != null) {
            spans = trace.getJSONArray("spans");
        }
        for (Object alarmInfo : spans) {
            dealSpanData(skywalkingAlarmMessageList, (JSONObject) alarmInfo);
        }
    }

    private void dealSpanData(List<SkywalkingAlarmMessage> skywalkingAlarmMessageList, JSONObject alarmInfo) {
        JSONObject alarmInfoJson = alarmInfo;
        Boolean isError = (Boolean) alarmInfoJson.get("isError");
        if (isError) {
            String traceId = alarmInfoJson.getString("traceId");
            String endTime = alarmInfoJson.getString("endTime");
            String endpointName = alarmInfoJson.getString("endpointName");
            String component = alarmInfoJson.getString("component");
            String peer = alarmInfoJson.getString("peer");
            String serviceCode = alarmInfoJson.getString("serviceCode");
            Map<String, String> tagsMap = new HashMap<>();
            JSONArray tags = alarmInfoJson.getJSONArray("tags");
            for (Object tag : tags) {
                JSONObject tagJson = (JSONObject) tag;
                String key = tagJson.getString("key");
                String value = tagJson.getString("value");
                tagsMap.put(key, value);
            }
            JSONArray logs = alarmInfoJson.getJSONArray("logs");
            Map<String, String> logsMap = new HashMap<>(16);
            if (logs != null && !logs.isEmpty()) {
                parseLogData(logs, logsMap);
            }
            SkywalkingAlarmMessage skywalkingAlarmMessage = new SkywalkingAlarmMessage(traceId, endTime,
                    endpointName, component, peer, serviceCode, tagsMap, logsMap);
            skywalkingAlarmMessageList.add(skywalkingAlarmMessage);
        }
    }

    private void parseLogData(JSONArray logs, Map<String, String> logsMap) {
        JSONObject logJson = (JSONObject) logs.get(0);
        JSONArray logDatas = logJson.getJSONArray("data");
        if (logDatas != null) {
            for (Object logdata : logDatas) {
                JSONObject logDataJson = (JSONObject) logdata;
                String key = logDataJson.getString("key");
                if ("event".equals(key)) {
                    continue;
                }
                String value = "";
                if ("stack".equals(key)) {
                    value = logDataJson.getString("value");
                    if (value != null && value.getBytes().length > 16000) {
                        byte[] newBytes = Arrays.copyOfRange(value.getBytes(), 0, 16000);
                        value = new String(newBytes);
                    }
                } else {
                    value = logDataJson.getString("value");
                }
                logsMap.put(key, value);
            }
        }
    }
}
