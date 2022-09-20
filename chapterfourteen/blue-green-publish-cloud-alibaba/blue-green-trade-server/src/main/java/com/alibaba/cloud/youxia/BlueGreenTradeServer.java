package com.alibaba.cloud.youxia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BlueGreenTradeServer {
    public static void main(String[] args) {
        SpringApplication.run(BlueGreenTradeServer.class, args);
    }
}
