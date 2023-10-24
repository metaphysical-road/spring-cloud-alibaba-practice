package com.alibaba.cloud.youxia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
//@Data
@Configuration
@ConfigurationProperties(prefix = "gray.publish.order")
public class GrayConfig {
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getGrayType() {
        return grayType;
    }

    public void setGrayType(String grayType) {
        this.grayType = grayType;
    }

    private String label;
    private String grayType;
}
