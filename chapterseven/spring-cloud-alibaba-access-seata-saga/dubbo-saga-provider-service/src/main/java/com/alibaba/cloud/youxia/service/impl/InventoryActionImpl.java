package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.service.InventoryAction;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@DubboService(version = "1.0.0",group="SEATA_GROUP")
//@Service(value = "inventoryAction")
public class InventoryActionImpl implements InventoryAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryActionImpl.class);

    //扣减
    @Override
    public boolean reduce(String businessKey, int count) {
        LOGGER.info("reduce inventory succeed, count: " + count + ", businessKey:" + businessKey);
        return true;
    }
    //补偿扣减
    @Override
    public boolean compensateReduce(String businessKey) {
        LOGGER.info("compensate reduce inventory succeed, businessKey:" + businessKey);
        return true;
    }
}
