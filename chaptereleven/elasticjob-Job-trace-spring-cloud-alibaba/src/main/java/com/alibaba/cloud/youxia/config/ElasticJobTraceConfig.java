package com.alibaba.cloud.youxia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Data
@RefreshScope
@ConfigurationProperties(prefix = "elasticjob.trace")
public class ElasticJobTraceConfig {
    private String isException;

    public String getIsException() {
        return isException;
    }

    public void setIsException(String isException) {
        this.isException = isException;
    }

    public String getOnSleep() {
        return onSleep;
    }

    public void setOnSleep(String onSleep) {
        this.onSleep = onSleep;
    }

    public Long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Long sleepTime) {
        this.sleepTime = sleepTime;
    }

    private String onSleep;
    private Long sleepTime;
}
