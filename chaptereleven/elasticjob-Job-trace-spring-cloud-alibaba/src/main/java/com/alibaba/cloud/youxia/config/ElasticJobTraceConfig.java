package com.alibaba.cloud.youxia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@RefreshScope
@ConfigurationProperties(prefix = "elasticjob.trace")
public class ElasticJobTraceConfig {
    private String isException;
    private String onSleep;
    private Long sleepTime;
}
