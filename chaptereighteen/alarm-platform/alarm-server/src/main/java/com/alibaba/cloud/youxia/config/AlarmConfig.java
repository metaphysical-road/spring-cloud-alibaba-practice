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
//@Data
@ConfigurationProperties(prefix = "alarm.config")
public class AlarmConfig {
    private String secret;
    private String webhook;
    private String skyWalkingUrl;
    private Boolean globalSwitch;
    private List<String> whiteServiceList;
    private String principalConfigs;

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

    public String getSkyWalkingUrl() {
        return skyWalkingUrl;
    }

    public void setSkyWalkingUrl(String skyWalkingUrl) {
        this.skyWalkingUrl = skyWalkingUrl;
    }

    public Boolean getGlobalSwitch() {
        return globalSwitch;
    }

    public void setGlobalSwitch(Boolean globalSwitch) {
        this.globalSwitch = globalSwitch;
    }

    public List<String> getWhiteServiceList() {
        return whiteServiceList;
    }

    public void setWhiteServiceList(List<String> whiteServiceList) {
        this.whiteServiceList = whiteServiceList;
    }

    public String getPrincipalConfigs() {
        return principalConfigs;
    }

    public void setPrincipalConfigs(String principalConfigs) {
        this.principalConfigs = principalConfigs;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    public String getSkyOapConfigs() {
        return skyOapConfigs;
    }

    public void setSkyOapConfigs(String skyOapConfigs) {
        this.skyOapConfigs = skyOapConfigs;
    }

    private Integer expireTime;
    private String skyOapConfigs;
}
