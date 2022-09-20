package com.alibaba.cloud.youxia.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "gray.publish.traffic")
public class GrayConfig {
    private String label;
    private String grayType;
    private String server;
    private String threadOpen;

    public void setGrayType(String grayType) {
        this.grayType = grayType;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getGrayType() {
        return grayType;
    }

    public String getLabel() {
        return label;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getThreadOpen() {
        return threadOpen;
    }

    public void setThreadOpen(String threadOpen) {
        this.threadOpen = threadOpen;
    }
}

