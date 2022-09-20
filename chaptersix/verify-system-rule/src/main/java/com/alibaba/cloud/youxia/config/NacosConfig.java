package com.alibaba.cloud.youxia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties(prefix = "youxia")
public class NacosConfig {

    private String enablecpuandrt;

    private String enableaddthreadqps;

    public boolean isEnableaddthreadqps() {
        return enableaddthreadqps.equals("1")?true:false;
    }

    public boolean isEnablecpuandrt() {
        return enablecpuandrt.equals("1")?true:false;
    }

    public String getEnableaddthreadqps() {
        return enableaddthreadqps;
    }

    public String getEnablecpuandrt() {
        return enablecpuandrt;
    }

    public void setEnableaddthreadqps(String enableaddthreadqps) {
        this.enableaddthreadqps = enableaddthreadqps;
    }

    public void setEnablecpuandrt(String enablecpuandrt) {
        this.enablecpuandrt = enablecpuandrt;
    }
}
