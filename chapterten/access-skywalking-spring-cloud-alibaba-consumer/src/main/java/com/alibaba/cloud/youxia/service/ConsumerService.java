package com.alibaba.cloud.youxia.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
@Service
public class ConsumerService {
    @DubboReference(version = "1.0.0",group = "access-skywalking-spring-cloud-alibaba-provider")
    private SkywalkingService skywalkingService;

    public String consumer(){
        return skywalkingService.skywalkingServiceProvider();
    }
}
