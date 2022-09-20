package com.alibaba.cloud.youxia.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
@Service
public class ConsumerService {
    @DubboReference(version = "1.0.0", group = "skywalking-kafka-provider")
    private SkywalkingKafkaService skywalkingKafkaService;

    public String consumer() {
        return skywalkingKafkaService.skywalkingKafkaServiceProvider();
    }
}
