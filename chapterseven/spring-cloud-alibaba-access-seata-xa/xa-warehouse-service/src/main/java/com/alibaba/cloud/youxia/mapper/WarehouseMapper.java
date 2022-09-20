package com.alibaba.cloud.youxia.mapper;

import com.alibaba.cloud.youxia.entity.WarehouseEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WarehouseMapper {
    Integer notify(WarehouseEntity warehouseEntity);
}
