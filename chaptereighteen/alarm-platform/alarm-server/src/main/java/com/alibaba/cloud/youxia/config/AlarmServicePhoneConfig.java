package com.alibaba.cloud.youxia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@Data
@RefreshScope
@ConfigurationProperties(prefix = "alarm.service")
public class AlarmServicePhoneConfig {
    private Map<String, String> phoneNumber;
}
