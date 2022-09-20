package com.alibaba.cloud.youxia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "redis.node")
public class JedisConfig {
    private Integer maxTotal;
    private String host;
    private Integer port;
    private String password;
}
