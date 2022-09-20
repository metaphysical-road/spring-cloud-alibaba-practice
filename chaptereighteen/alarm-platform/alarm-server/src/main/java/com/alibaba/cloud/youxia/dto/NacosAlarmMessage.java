package com.alibaba.cloud.youxia.dto;

import lombok.Data;
import java.util.Date;
import java.util.Objects;

@Data
public class NacosAlarmMessage {

    private String NameSpace;
    private String serviceName;
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
