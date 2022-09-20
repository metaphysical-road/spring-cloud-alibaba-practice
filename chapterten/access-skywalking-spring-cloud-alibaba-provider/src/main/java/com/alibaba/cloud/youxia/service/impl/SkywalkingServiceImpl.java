package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.service.SkywalkingService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0",group = "access-skywalking-spring-cloud-alibaba-provider")
public class SkywalkingServiceImpl implements SkywalkingService {
    @Override
    public String skywalkingServiceProvider() {
        return "access skywalking!";
    }
}
