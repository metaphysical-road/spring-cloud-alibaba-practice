package com.alibaba.cloud.youxia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Data
@RefreshScope
@ConfigurationProperties(prefix = "order.alarm")
public class OrderAlarmConfig {
    private String delayTime;
        private String consumerDelayTime;

    public String getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(String delayTime) {
        this.delayTime = delayTime;
    }

    public String getConsumerDelayTime() {
        return consumerDelayTime;
    }

    public void setConsumerDelayTime(String consumerDelayTime) {
        this.consumerDelayTime = consumerDelayTime;
    }
}
