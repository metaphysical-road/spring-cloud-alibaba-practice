package com.alibaba.cloud.youxia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
@EnableDiscoveryClient
//@ImportResource(locations = "classpath:spring/seata-dubbo-reference.xml")
public class DubboSagaConsumerApplication {
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(DubboSagaConsumerApplication.class, args);
    }
}
