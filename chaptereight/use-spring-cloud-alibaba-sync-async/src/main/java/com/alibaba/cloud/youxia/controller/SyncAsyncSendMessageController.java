package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.rocketmq.SyncAsyncSendService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value = "/syncAsyncSendMessage")
public class SyncAsyncSendMessageController {
    @Resource
    private SyncAsyncSendService syncAsyncSendService;

    @GetMapping(value = "/asyncSendMessage")
    public String sendAsyncMessage(){
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        executorService.execute(new AsyncSendMessage());
        return "成功";
    }

    class AsyncSendMessage implements Runnable{
        @Override
        public void run() {
            while (true){
                syncAsyncSendService.sendAsyncMessage(RandomUtils.nextLong()+"异步消息");
                try{
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @GetMapping(value = "/syncSendMessage")
    public String syncSendMessage(){
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        executorService.execute(new SyncSendMessage());
        return "成功";
    }

    class SyncSendMessage implements Runnable{
        @Override
        public void run() {
            while (true){
                syncAsyncSendService.sendSyncMessage(RandomUtils.nextLong()+"同步消息");
                try{
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
