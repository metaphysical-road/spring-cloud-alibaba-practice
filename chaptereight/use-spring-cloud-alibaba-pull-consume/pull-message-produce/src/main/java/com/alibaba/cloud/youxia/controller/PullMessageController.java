package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.rocketmq.PullMessageSendService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value = "pullmessage")
public class PullMessageController {

    @Autowired
    private PullMessageSendService pullMessageSendService;

    @GetMapping(value = "/sendMessage")
    public String sendMessage(){
        ExecutorService executorService= Executors.newFixedThreadPool(1);
        executorService.execute(new SendMessage());
        return "true";
    }

    class SendMessage implements Runnable{
        @Override
        public void run() {
            while (true){
                pullMessageSendService.sendMessage(RandomUtils.nextLong()+"的消息");
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
