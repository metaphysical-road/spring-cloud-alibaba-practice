package com.alibaba.cloud.youxia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@RefreshScope
@ConfigurationProperties(prefix = "order.alarm")
public class OrderAlarmConfig {
    private String delayTime;
        private String consumerDelayTime;
}
