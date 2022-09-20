package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.manager.StorageManager;
import com.alibaba.cloud.youxia.service.AtStorageService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(version = "1.0.0",group = "SEATA_GROUP")
public class AtStorageServiceImpl implements AtStorageService {
    @Resource
    private StorageManager storageManager;
    @Override
    public boolean deductInventory(Long goodId,Integer num) {
        return storageManager.deductInventory(goodId,num);
    }
}
