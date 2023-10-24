package com.alibaba.cloud.youxia.config;

import lombok.Data;
import lombok.ToString;

//@Data
//@ToString
public class SnowflakeInfo {
    /**
     * ip
     */
    private String ip;
    /**
     * 数据中心id
     */
    private Long dataCenterId;
    /**
     * 机器id
     */
    private Long machineId;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getDataCenterId() {
        return dataCenterId;
    }

    public void setDataCenterId(Long dataCenterId) {
        this.dataCenterId = dataCenterId;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }
}
