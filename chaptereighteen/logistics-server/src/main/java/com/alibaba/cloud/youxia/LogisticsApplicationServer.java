package com.alibaba.cloud.youxia;

import com.alibaba.cloud.youxia.rocketmq.SendServiceSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({SendServiceSource.class})
public class LogisticsApplicationServer {
    public static void main(String[] args) {
        SpringApplication.run(LogisticsApplicationServer.class, args);
    }
}
