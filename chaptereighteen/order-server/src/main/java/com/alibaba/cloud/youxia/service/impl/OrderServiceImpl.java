package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.config.OrderAlarmConfig;
import com.alibaba.cloud.youxia.manager.OrderManager;
import com.alibaba.cloud.youxia.service.DistributedService;
import com.alibaba.cloud.youxia.service.eighteen.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@DubboService(version = "1.0.0",group = "order-server",timeout = 3000)
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderManager orderManager;
    @DubboReference(version = "1.0.0",group = "distributed-uuid-server")
    private DistributedService distributedService;
    @Autowired
    private OrderAlarmConfig orderAlarmConfig;
    @Override
    public Long createOrder(String goodId) {
        //模拟创建订单
        Long orderId=distributedService.nextId();
        //返回订单号
        orderManager.deductionInventory(goodId);
        try{
            Thread.sleep(Long.valueOf(orderAlarmConfig.getDelayTime()));
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return orderId;
    }
}
