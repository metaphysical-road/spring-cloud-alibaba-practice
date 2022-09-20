package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.transation.ProducerTransationService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value = "/sendTransationMessage")
public class TransationMessageController {

    @Autowired
    private ProducerTransationService producerTransationService;

    @RequestMapping(value = "/message")
    public String sendTransationMessage() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new sendTransationMessage());
        return "成功";
    }

    class sendTransationMessage implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    producerTransationService.sendMessage();
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException interruptedException) {
                    System.out.println(interruptedException.getMessage());
                } catch (MQClientException mqClientException) {
                    System.out.println(mqClientException.getMessage());
                }
            }
        }
    }
}
