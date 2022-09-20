package com.alibaba.cloud.youxia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BlueGreenOrderServer {
    public static void main(String[] args) {
        SpringApplication.run(BlueGreenOrderServer.class, args);
    }
}
