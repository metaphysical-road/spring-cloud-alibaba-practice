package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.action.StorageTccAction;
import com.alibaba.cloud.youxia.service.TccStorageService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(version = "1.0.0",group = "SEATA_GROUP")
public class TccStorageServiceImpl implements TccStorageService {
    @Resource
    private StorageTccAction storageTccAction;
    @Override
    public void decreaseStorage(Long productId, Integer count) {
        storageTccAction.prepareDecreaseStorage(null, productId, count);
    }
}
