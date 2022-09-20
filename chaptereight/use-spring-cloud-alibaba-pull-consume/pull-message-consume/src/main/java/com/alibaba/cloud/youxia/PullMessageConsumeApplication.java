package com.alibaba.cloud.youxia;

import com.alibaba.cloud.youxia.rocketmq.PullMessageConsumeSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({ PullMessageConsumeSink.class })
public class PullMessageConsumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PullMessageConsumeApplication.class, args);
    }
}
