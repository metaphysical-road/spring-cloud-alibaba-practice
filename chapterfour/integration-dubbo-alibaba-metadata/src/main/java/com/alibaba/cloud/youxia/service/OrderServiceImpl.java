package com.alibaba.cloud.youxia.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "integration-dubbo-alibaba-metadata",version = "1.0.0")
public class OrderServiceImpl implements OrderService {

    @Override
    public String getOrderName() {
        return "order";
    }
}
