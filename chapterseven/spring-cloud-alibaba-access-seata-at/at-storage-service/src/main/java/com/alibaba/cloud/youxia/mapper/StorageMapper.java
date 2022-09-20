package com.alibaba.cloud.youxia.mapper;

import com.alibaba.cloud.youxia.bo.StorageBo;

public interface StorageMapper {
    Integer deductInventory(StorageBo storageBo);
}
