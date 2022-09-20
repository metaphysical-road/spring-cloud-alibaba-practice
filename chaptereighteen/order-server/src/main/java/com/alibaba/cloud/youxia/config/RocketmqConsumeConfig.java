package com.alibaba.cloud.youxia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Configuration
public class RocketmqConsumeConfig {
    @Resource
    private MySink mySink;
    @Autowired
    private OrderAlarmConfig orderAlarmConfig;
    @PostConstruct
    public void startConsume(){
        ExecutorService executorService= Executors.newFixedThreadPool(20);
        executorService.execute(new ConsumeMessage());
    }
    class ConsumeMessage implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(Long.valueOf(orderAlarmConfig.getConsumerDelayTime()));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            while (true) {
                mySink.input1();
            }
        }
    }
}
