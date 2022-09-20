package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.OrderService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "user-server",version = "1.0.0")
public class OrderServiceImpl implements OrderService {
    @Override
    public String getOrderInfo() {
        return "orderInfo";
    }
}
