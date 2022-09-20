package com.alibaba.cloud.youxia.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;
@ToString
public class SkyOapConfigDTO implements Serializable {

    private static final long serialVersionUID = -2279647623600217691L;
    List<String> ipConfigList;
    String platform;

    public List<String> getIpConfigList() {
        return ipConfigList;
    }

    public void setIpConfigList(List<String> ipConfigList) {
        this.ipConfigList = ipConfigList;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
