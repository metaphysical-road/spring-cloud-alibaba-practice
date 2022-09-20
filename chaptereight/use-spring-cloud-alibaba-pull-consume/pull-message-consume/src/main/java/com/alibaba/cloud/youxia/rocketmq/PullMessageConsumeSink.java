package com.alibaba.cloud.youxia.rocketmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.messaging.SubscribableChannel;

public interface PullMessageConsumeSink {
    @Input("input1")
    SubscribableChannel input1();
    @Input("input2")
    PollableMessageSource input2();
}
