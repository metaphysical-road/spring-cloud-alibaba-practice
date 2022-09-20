package com.alibaba.cloud.youxia.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "alibaba.redisson")
public class RedissonConfig {

}
