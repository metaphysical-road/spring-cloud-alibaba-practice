package com.alibaba.cloud.youxia.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RefreshScope
@ConfigurationProperties(prefix = "snowflake")
public class SnowflakeConfig {
    private List<SnowflakeInfo> config = new ArrayList<>();

    public List<SnowflakeInfo> getConfig() {
        return config;
    }

    public void setConfig(List<SnowflakeInfo> config) {
        this.config = config;
    }
}
