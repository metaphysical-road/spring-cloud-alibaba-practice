package com.alibaba.cloud.youxia.manager;

import com.alibaba.cloud.youxia.service.eighteen.LogisticsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class InventoryManager {
    @DubboReference(version = "1.0.0",group = "logistics-server",timeout = 3000)
    private LogisticsService logisticsService;

    public boolean deliverGoods(String goodId,Long orderId){
        return logisticsService.deliverGoods(goodId,orderId);
    }
}
