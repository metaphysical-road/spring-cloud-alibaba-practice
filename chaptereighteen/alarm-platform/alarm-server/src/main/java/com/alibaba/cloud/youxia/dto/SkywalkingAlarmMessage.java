package com.alibaba.cloud.youxia.dto;
import lombok.Data;
import java.util.Map;
//@Data
public class SkywalkingAlarmMessage {
    private String traceId;
    private String endTime;
    private String endpointName;
    private String component;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPeer() {
        return peer;
    }

    public void setPeer(String peer) {
        this.peer = peer;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Map<String, String> getTagsMap() {
        return tagsMap;
    }

    public void setTagsMap(Map<String, String> tagsMap) {
        this.tagsMap = tagsMap;
    }

    public Map<String, String> getLogsMap() {
        return logsMap;
    }

    public void setLogsMap(Map<String, String> logsMap) {
        this.logsMap = logsMap;
    }

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
