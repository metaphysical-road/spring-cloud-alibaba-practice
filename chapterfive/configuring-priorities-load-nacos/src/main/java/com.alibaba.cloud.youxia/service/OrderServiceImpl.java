package com.alibaba.cloud.youxia.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class OrderServiceImpl implements OrderService{

    @Autowired
    private Config config;

    @Override
    public String getOrderInfo() {
        return config.getName();
    }
}
