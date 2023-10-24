package com.alibaba.cloud.youxia.dto;

import lombok.Data;
import java.util.Date;
import java.util.Objects;

//@Data
public class NacosAlarmMessage {

    private String NameSpace;
    private String serviceName;

    public String getNameSpace() {
        return NameSpace;
    }

    public void setNameSpace(String nameSpace) {
        NameSpace = nameSpace;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    private String ip;
    private String message;
    private Date time;
    public NacosAlarmMessage(String nameSpace, String serviceName, String ip, String message, Date time) {
        NameSpace = nameSpace;
        this.serviceName = serviceName;
        this.ip = ip;
        this.message = message;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        NacosAlarmMessage that = (NacosAlarmMessage) o;
        return Objects.equals(NameSpace, that.NameSpace) && Objects.equals(serviceName, that.serviceName) && Objects
            .equals(ip, that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NameSpace, serviceName, ip);
    }
}
