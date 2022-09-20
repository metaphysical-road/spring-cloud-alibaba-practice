package com.alibaba.cloud.youxia.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {
    @Input("input1")
    SubscribableChannel input1();
}
