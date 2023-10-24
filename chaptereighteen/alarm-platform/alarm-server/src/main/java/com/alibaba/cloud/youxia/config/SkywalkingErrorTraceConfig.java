package com.alibaba.cloud.youxia.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
/**
 * @author huxian
 * 告警服务查询Skywalking的链路错误信息的配置信息
 */
@Component
//@Data
@RefreshScope
@ConfigurationProperties(prefix = "query.skywalking.error")
public class SkywalkingErrorTraceConfig {
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReqApi() {
        return reqApi;
    }

    public void setReqApi(String reqApi) {
        this.reqApi = reqApi;
    }

    public String getSksecret() {
        return sksecret;
    }

    public void setSksecret(String sksecret) {
        this.sksecret = sksecret;
    }

    public String getSkwebhook() {
        return skwebhook;
    }

    public void setSkwebhook(String skwebhook) {
        this.skwebhook = skwebhook;
    }

    private String reqApi;
    private String sksecret;
    private String skwebhook;
}
