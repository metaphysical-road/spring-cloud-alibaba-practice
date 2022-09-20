package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.config.PayAlarmConfig;
import com.alibaba.cloud.youxia.manager.PayManager;
import com.alibaba.cloud.youxia.service.eighteen.PayService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.math.BigDecimal;
@DubboService(version = "1.0.0",group = "pay-server")
public class PayServiceImpl implements PayService {
    @Autowired
    private PayAlarmConfig payAlarmConfig;
    @Resource
    private PayManager payManager;
    @Override
    public boolean pay(Long orderId, String goodId, BigDecimal amount) {
        System.out.println("完成支付");
        boolean payResult=true;
        //在完成支付之后，通知库存服务直接扣减仓库库存
        boolean inventoryResult=payManager.syncInventory(goodId,orderId);
        try{
            Thread.sleep(Long.valueOf(payAlarmConfig.getDelayTime()));
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return inventoryResult&payResult;
    }
}
