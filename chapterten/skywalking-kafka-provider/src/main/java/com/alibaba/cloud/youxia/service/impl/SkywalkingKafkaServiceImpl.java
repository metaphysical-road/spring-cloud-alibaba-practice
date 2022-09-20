package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.service.SkywalkingKafkaService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0",group = "skywalking-kafka-provider")
public class SkywalkingKafkaServiceImpl implements SkywalkingKafkaService {
    @Override
    public String skywalkingKafkaServiceProvider() {
        return "provider kafka message!";
    }
}
