package com.alibaba.cloud.youxia;

import com.alibaba.cloud.youxia.consumer.OrderServiceConsumer;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class ConsumerBootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        OrderServiceConsumer greetingServiceConsumer = context.getBean(OrderServiceConsumer.class);
        String hello = greetingServiceConsumer.getOrderName();
        System.out.println("result: " + hello);
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "org.apache.dubbo.samples.action")
    @PropertySource("classpath:/spring/dubbo-consumer.properties")
    @ComponentScan(value = {"com.alibaba.cloud.youxia.consumer"})
    static class ConsumerConfiguration {
    }
}
