package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.rocketmq.OrderSyncMessageSendService;
import com.alibaba.cloud.youxia.service.eighteen.LogisticsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;

@DubboService(version = "1.0.0",group = "logistics-server",timeout = 3000)
public class LogisticsServiceImpl implements LogisticsService {
    @Autowired
    private OrderSyncMessageSendService orderSyncMessageSendService;
    @Override
    public boolean deliverGoods(String goodId,Long orderId) {
        System.out.println("物流服务发货！");
        orderSyncMessageSendService.send("如果发货成功，给订单服务发送订单已经支付完成的物流状态");
        return true;
    }
}
