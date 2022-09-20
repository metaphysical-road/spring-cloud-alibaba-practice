package com.alibaba.cloud.youxia.manager;

import com.alibaba.cloud.youxia.service.eighteen.InventoryService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class PayManager {
    @DubboReference(version = "1.0.0",group = "inventory-server",timeout = 3000)
    private InventoryService inventoryService;
    public boolean syncInventory(String goodId,Long orderId){
        return inventoryService.syncInventory(goodId,orderId);
    }
}
