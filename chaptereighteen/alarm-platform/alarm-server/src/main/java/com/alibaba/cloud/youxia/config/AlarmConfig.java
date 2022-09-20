package com.alibaba.cloud.youxia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import java.util.List;

/**
 * @author huxian
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "alarm.config")
@Data
public class AlarmConfig {
    private String secret;
    private String webhook;
    private String skyWalkingUrl;
    private Boolean globalSwitch;
    private List<String> whiteServiceList;
    private String principalConfigs;
    private Integer expireTime;
    private String skyOapConfigs;
}
