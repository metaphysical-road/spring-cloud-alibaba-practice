package com.alibaba.cloud.youxia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix = "query.nacos")
@Data
@RefreshScope
public class NacosAlarmConfig {
    private String platform;
    private String nacosIp;
    private String nacosInstanceAlarmApi;
    private String nacosServiceListApi;
    private String nacosInstanceListApi;
    private String nacosNameSpaceId;
}
