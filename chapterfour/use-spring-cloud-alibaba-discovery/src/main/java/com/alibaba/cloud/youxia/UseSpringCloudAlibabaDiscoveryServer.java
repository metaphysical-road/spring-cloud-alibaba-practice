package com.alibaba.cloud.youxia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient(autoRegister = true)
@SpringBootApplication
public class UseSpringCloudAlibabaDiscoveryServer {

    public static void main(String[] args) {
        SpringApplication.run(UseSpringCloudAlibabaDiscoveryServer.class, args);
    }

}
