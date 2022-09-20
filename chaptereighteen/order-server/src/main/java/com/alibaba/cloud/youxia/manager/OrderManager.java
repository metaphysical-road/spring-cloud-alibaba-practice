package com.alibaba.cloud.youxia.manager;

import com.alibaba.cloud.youxia.service.eighteen.GoodService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class OrderManager {
    @DubboReference(version = "1.0.0",group = "good-server")
    private GoodService goodService;
    public boolean deductionInventory(String goodId){
        return goodService.deductionInventory(goodId);
    }

}
