package com.alibaba.cloud.youxia.rocketmq;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PullLongPollMessageReceiveService {
    @Resource
    private PullMessageConsumeSink pullMessageConsumeSink;

    public void pollMessage(){
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        executorService.execute(new PollMessage());
    }
    class PollMessage implements Runnable{
        @Override
        public void run() {
            while (true){
                pullMessageConsumeSink.input2().poll(m -> {
                    String payload = (String) m.getPayload();
                    System.out.println("pull msg: " + payload);
                }, new ParameterizedTypeReference<String>() {
                });
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
