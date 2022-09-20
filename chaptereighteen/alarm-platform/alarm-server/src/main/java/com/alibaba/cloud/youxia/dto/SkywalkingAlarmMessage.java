package com.alibaba.cloud.youxia.dto;
import lombok.Data;
import java.util.Map;
@Data
public class SkywalkingAlarmMessage {
    private String traceId;
    private String endTime;
    private String endpointName;
    private String component;
    private String peer;
    private String serviceCode;
    private Map<String, String> tagsMap;
    private Map<String, String> logsMap;
    public SkywalkingAlarmMessage(){};
    public SkywalkingAlarmMessage(String traceId, String endTime, String endpointName, String component, String peer,
        String serviceCode, Map<String, String> tagsMap, Map<String, String> logsMap) {
        this.traceId = traceId;
        this.endTime = endTime;
        this.endpointName = endpointName;
        this.component = component;
        this.peer = peer;
        this.serviceCode = serviceCode;
        this.tagsMap = tagsMap;
        this.logsMap = logsMap;
    }
}
