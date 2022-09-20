package com.alibaba.cloud.youxia.rocketmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SendServiceSource {
    @Output("output1")
    MessageChannel output1();
}
