package com.alibaba.cloud.youxia.rocketmq;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class PushMessageReceiveService {
    //采用Push模式（发布订阅模式）消费消息
    @StreamListener("input1")
    public void receiveInput1(String receiveMsg) {
        System.out.println("input1 receive: " + receiveMsg);
    }
}
