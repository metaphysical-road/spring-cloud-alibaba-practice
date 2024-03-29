package com.alibaba.cloud.youxia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;
/**
 * @author huxian
 */
//@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "rocketmq")
public class RocketMQWhiteListConfig {
    public List<String> getRocketMQWhiteList() {
        return rocketMQWhiteList;
    }

    public void setRocketMQWhiteList(List<String> rocketMQWhiteList) {
        this.rocketMQWhiteList = rocketMQWhiteList;
    }

    private List<String> rocketMQWhiteList;
}
