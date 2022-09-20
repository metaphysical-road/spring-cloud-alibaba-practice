package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.config.InventoryAlarmConfig;
import com.alibaba.cloud.youxia.manager.InventoryManager;
import com.alibaba.cloud.youxia.service.eighteen.InventoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@DubboService(version = "1.0.0",group = "inventory-server",timeout = 3000)
public class InventoryServiceImpl implements InventoryService {
    @Resource
    private InventoryManager inventoryManager;
    @Autowired
    private InventoryAlarmConfig inventoryAlarmConfig;

    public boolean syncInventory(String goodId,Long orderId) {
        System.out.println("执行扣减仓库库存");
        boolean inventoryResult=true;
        //仓库库存扣减成功之后，通知物流服务发货
        boolean deliverResult=inventoryManager.deliverGoods(goodId,orderId);
        try{
            Thread.sleep(Long.valueOf(inventoryAlarmConfig.getDelayTime()));
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return inventoryResult&deliverResult;
    }
}
