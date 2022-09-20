package com.alibaba.cloud.youxia.rocketmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PullMessageSendService {
    @Autowired
    private PullMessageSource pullMessageSource;

    public void sendMessage(String msg){
        pullMessageSource.output1().send(MessageBuilder.withPayload(msg).build());
        pullMessageSource.output2().send(MessageBuilder.withPayload(msg).build());
    }
}
