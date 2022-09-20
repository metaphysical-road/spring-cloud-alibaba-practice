package com.alibaba.cloud.youxia.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {
    @Output("output1")
    MessageChannel output1();
}
