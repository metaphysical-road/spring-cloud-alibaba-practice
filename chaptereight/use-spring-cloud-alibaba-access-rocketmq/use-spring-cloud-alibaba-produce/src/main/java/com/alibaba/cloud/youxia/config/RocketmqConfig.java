package com.alibaba.cloud.youxia.config;

import com.alibaba.cloud.youxia.SenderService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class RocketmqConfig {
    @Autowired
    private SenderService senderService;

    public void startRunSendMessage(){
        ExecutorService executorService= Executors.newFixedThreadPool(1 );
        executorService.execute(new SendMessage());
    }
    class SendMessage implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                    String msgContent = "msg-" + RandomUtils.nextLong();
                    senderService.send(msgContent);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
