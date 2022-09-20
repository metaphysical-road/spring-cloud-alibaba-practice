package com.alibaba.cloud.youxia.consumer;

import com.alibaba.cloud.youxia.service.OrderServiceProvider;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component("orderServiceConsumer")
public class OrderServiceConsumer {
    @DubboReference(group = "example8", version = "1.0.0")
    private OrderServiceProvider orderServiceProvider;

    public String getOrderName() {
        return orderServiceProvider.getOrderName();
    }
}

