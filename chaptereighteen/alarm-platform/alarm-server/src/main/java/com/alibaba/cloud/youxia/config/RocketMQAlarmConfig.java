package com.alibaba.cloud.youxia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
/**
 * @author huxian
 * 告警服务调用RocketMQ的Open API，并获取指定主题的消息堆积数据。
 */
@Configuration
//@Data
@ConfigurationProperties(prefix = "query.rocketmq")
@RefreshScope
public class RocketMQAlarmConfig {
    private String groupListUrl;
    private String groupInfoUrl;

    public String getGroupListUrl() {
        return groupListUrl;
    }

    public void setGroupListUrl(String groupListUrl) {
        this.groupListUrl = groupListUrl;
    }

    public String getGroupInfoUrl() {
        return groupInfoUrl;
    }

    public void setGroupInfoUrl(String groupInfoUrl) {
        this.groupInfoUrl = groupInfoUrl;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getDelayTotal() {
        return delayTotal;
    }

    public void setDelayTotal(String delayTotal) {
        this.delayTotal = delayTotal;
    }

    private String secret;
    private String webhook;
    private String delayTotal;

}
