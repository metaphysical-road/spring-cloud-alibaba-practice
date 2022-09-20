package com.alibaba.cloud.youxia.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class RocketmqConsumeConfig {

    @Resource
    private MySink mySink;

    @PostConstruct
    public void startConsume(){
        ExecutorService executorService= Executors.newFixedThreadPool(20);
        executorService.execute(new ConsumeMessage());
    }
    class ConsumeMessage implements Runnable{
        @Override
        public void run() {
            while (true){
                mySink.input1();
            }
        }
    }
}
