package com.alibaba.cloud.youxia;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan(value = "com.alibaba.cloud.youxia")
@EnableDubbo
public class UseNacosSpringBootServer {
    public static void main(String[] args) {
        SpringApplication.run(UseNacosSpringBootServer.class, args);
    }
}
