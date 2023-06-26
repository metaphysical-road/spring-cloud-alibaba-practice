package com.alibaba.cloud.youxia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 第一种消费消息模式：监听器自动消费消息
 * 第二种消费消息的模式：RocketMQTemplate模式
 * 线上只允许使用一种
 */
@SpringBootApplication
@EnableDiscoveryClient
public class 
UseSpringBootConsumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(UseSpringBootConsumeApplication.class, args);
    }
}
