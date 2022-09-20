package com.alibaba.cloud.youxia.dto;

import lombok.Data;
import java.io.Serializable;

public class AlarmPrincipalDTO implements Serializable {

    private static final long serialVersionUID = -3467669917165511820L;
    private String serviceName;
    private String principalPhoneNum;

    public String getServiceName() {
        return serviceName;
    }

    public String getPrincipalPhoneNum() {
        return principalPhoneNum;
    }

    public void setPrincipalPhoneNum(String principalPhoneNum) {
        this.principalPhoneNum = principalPhoneNum;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
