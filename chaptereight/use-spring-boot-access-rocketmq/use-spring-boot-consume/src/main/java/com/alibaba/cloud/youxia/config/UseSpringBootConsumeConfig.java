package com.alibaba.cloud.youxia.config;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class UseSpringBootConsumeConfig {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @PostConstruct
    public void startConsume(){
        ExecutorService executorService=Executors.newCachedThreadPool();
        executorService.submit(new ConsumeMessage());
    }

    class ConsumeMessage implements Runnable {
        @Override
        public void run() {
            while (true) {
                List<String> result = rocketMQTemplate.receive(String.class);
                System.out.println("消费结果：" + result.toString());
            }
        }
    }
}
