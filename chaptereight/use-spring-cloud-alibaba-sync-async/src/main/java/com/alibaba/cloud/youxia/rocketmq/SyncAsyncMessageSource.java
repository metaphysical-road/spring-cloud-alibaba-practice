package com.alibaba.cloud.youxia.rocketmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SyncAsyncMessageSource {
    @Output("output1")
    MessageChannel output1();
    @Output("output2")
    MessageChannel output2();
}
