package com.alibaba.cloud.youxia;

import com.alibaba.cloud.youxia.rocketmq.PullMessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({ PullMessageSource.class })
public class PullMessageProduceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PullMessageProduceApplication.class, args);
    }
}
