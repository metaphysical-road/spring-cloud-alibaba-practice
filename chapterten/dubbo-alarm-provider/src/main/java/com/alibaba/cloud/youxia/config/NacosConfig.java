package com.alibaba.cloud.youxia.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "spring.youxia.sleep")
public class NacosConfig {
    private String time;
    private String open;
    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
    }
    public void setOpen(String open) {
        this.open = open;
    }
    public String getOpen() {
        return open;
    }
}
