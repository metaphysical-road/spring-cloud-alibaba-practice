package com.alibaba.cloud.youxia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.alibaba.cloud.youxia.mapper")
public class UseSpringCloudAlibabaSeataApplication {
    public static void main(String[] args) {
        SpringApplication.run(UseSpringCloudAlibabaSeataApplication.class, args);
    }
}
