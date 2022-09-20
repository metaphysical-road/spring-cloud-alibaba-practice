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
@Data
@RefreshScope
@ConfigurationProperties(prefix = "query.skywalking.error")
public class SkywalkingErrorTraceConfig {
    private String time;
    private String reqApi;
    private String sksecret;
    private String skwebhook;
}
