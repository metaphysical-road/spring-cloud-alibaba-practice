package com.alibaba.cloud.youxia.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "example8",version = "1.0.0")
public class OrderServiceProviderImpl implements OrderServiceProvider {
    @Override
    public String getOrderName() {
        return "orderName";
    }
}
