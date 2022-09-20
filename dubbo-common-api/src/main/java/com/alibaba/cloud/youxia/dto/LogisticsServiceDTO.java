package com.alibaba.cloud.youxia.dto;

import java.io.Serializable;

public class LogisticsServiceDTO implements Serializable {
    private Long logisticsId;
    private Integer status;

    public void setLogisticsId(Long logisticsId) {
        this.logisticsId = logisticsId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getLogisticsId() {
        return logisticsId;
    }
}
