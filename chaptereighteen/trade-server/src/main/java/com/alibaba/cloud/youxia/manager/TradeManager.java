package com.alibaba.cloud.youxia.manager;

import com.alibaba.cloud.youxia.service.eighteen.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class TradeManager {
    @DubboReference(version = "1.0.0",group = "order-server",timeout = 3000)
    private OrderService orderService;
    public Long createOrder(String goodId){
        return orderService.createOrder(goodId);
    }
}
