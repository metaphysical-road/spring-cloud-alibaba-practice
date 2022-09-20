package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.config.GoodAlarmConfig;
import com.alibaba.cloud.youxia.service.eighteen.GoodService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0.0",group = "good-server")
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodAlarmConfig goodAlarmConfig;
    @Override
    public boolean deductionInventory(String goodId) {
        System.out.println("锁住当前商品库存");
        try{
            Thread.sleep(Long.valueOf(goodAlarmConfig.getDelayTime()));
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
}
