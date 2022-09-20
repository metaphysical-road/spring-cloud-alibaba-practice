package com.alibaba.cloud.youxia.manager;

import com.alibaba.cloud.youxia.bo.StorageBo;
import com.alibaba.cloud.youxia.mapper.StorageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageManager {
    @Resource
    private StorageMapper storageMapper;

    public boolean deductInventory(Long goodId, Integer num) {
        StorageBo storageBo = new StorageBo();
        storageBo.setNum(num);
        storageBo.setGoodId(goodId);
        Integer result = storageMapper.deductInventory(storageBo);
        return result > 0 ? true : false;
    }
}
