package com.alibaba.cloud.youxia.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0",group = "example4-springcloud-user-server")
public class OrderServiceImpl implements OrderService {
    @Override
    public String getOrderInfo() {
        return "orderInfo";
    }
}
