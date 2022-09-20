package com.alibaba.cloud.youxia;

import com.alibaba.cloud.youxia.config.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({ MySink.class })
public class OrderApplicationServer {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplicationServer.class, args);
    }
}
