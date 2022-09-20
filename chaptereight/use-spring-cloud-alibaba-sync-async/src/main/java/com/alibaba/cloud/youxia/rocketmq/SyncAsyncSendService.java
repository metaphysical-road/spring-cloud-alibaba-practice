package com.alibaba.cloud.youxia.rocketmq;

import com.alibaba.cloud.youxia.rocketmq.SyncAsyncMessageSource;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SyncAsyncSendService {
    @Resource
    private SyncAsyncMessageSource syncAsyncMessageSource;

    public void sendSyncMessage(String msg){
        System.out.println("同步发送消息："+msg);
        syncAsyncMessageSource.output2().send(MessageBuilder.withPayload(msg).build());
    }
    public void sendAsyncMessage(String msg){
        System.out.println("异步发送消息："+msg);
        syncAsyncMessageSource.output1().send(MessageBuilder.withPayload(msg).build());
    }
}
