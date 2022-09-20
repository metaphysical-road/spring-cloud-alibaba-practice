package com.alibaba.cloud.youxia.config;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class UseSpringBootProduceConfig {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @PostConstruct
    public void startProduce(){
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.submit(new SendMessage());
    }

    class SendMessage implements Runnable{
        @Override
        public void run() {
            while (true) {
                SendResult sendResult = rocketMQTemplate.syncSend("use-spring-boot-access-rocketmq", "hello word!");
                System.out.println("sendResult:" + sendResult.toString());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
