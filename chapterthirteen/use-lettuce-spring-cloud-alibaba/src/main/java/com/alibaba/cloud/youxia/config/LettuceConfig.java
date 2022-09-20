package com.alibaba.cloud.youxia.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "alibaba.lettuce")
public class LettuceConfig {
    private String redisUrl;
    private String password;
    private List<String> hostUrlList;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setHostUrlList(List<String> hostUrlList) {
        this.hostUrlList = hostUrlList;
    }

    public void setRedisUrl(String redisUrl) {
        this.redisUrl = redisUrl;
    }

    public List<String> getHostUrlList() {
        String[] hostUrlArrays=null;
        if(StringUtils.isNotEmpty(redisUrl)){
            hostUrlArrays=redisUrl.split(",");
        }
        hostUrlList= Arrays.asList(hostUrlArrays);
        return hostUrlList;
    }

    public String getRedisUrl() {
        return redisUrl;
    }
}
