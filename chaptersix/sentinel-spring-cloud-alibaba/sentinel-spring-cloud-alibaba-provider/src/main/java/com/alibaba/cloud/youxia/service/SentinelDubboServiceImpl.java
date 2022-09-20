package com.alibaba.cloud.youxia.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0",group ="sentinel-spring-cloud-alibaba-provider")
public class SentinelDubboServiceImpl implements SentinelDubboService{
    @Override
    public String testSentinelFlow() {
        return "sentinelException";
    }
}
