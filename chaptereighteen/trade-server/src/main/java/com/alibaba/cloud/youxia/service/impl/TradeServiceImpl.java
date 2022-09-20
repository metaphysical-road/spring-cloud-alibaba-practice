package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.config.TradeAlarmConfig;
import com.alibaba.cloud.youxia.manager.TradeManager;
import com.alibaba.cloud.youxia.service.eighteen.TradeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@DubboService(version = "1.0.0",group = "trade-server")
public class TradeServiceImpl implements TradeService {
    @Resource
    private TradeManager tradeManager;
    @Autowired
    private TradeAlarmConfig tradeAlarmConfig;
    @Override
    public boolean buy(String goodId) {
        try{
            Thread.sleep(Long.valueOf(tradeAlarmConfig.getDelayTime()));
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return null!=tradeManager.createOrder(goodId)?true:false;
    }
}
