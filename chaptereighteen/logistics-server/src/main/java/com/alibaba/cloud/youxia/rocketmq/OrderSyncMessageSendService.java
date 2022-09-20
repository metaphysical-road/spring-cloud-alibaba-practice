package com.alibaba.cloud.youxia.rocketmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderSyncMessageSendService {
    @Autowired
    private SendServiceSource sendServiceSource;
    public void send(String msg) {
        sendServiceSource.output1().send(MessageBuilder.withPayload(msg).build());
    }
}
