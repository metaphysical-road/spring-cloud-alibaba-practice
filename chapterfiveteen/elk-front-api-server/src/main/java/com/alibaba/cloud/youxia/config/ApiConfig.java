package com.alibaba.cloud.youxia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

//@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "good.api")
public class ApiConfig {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
