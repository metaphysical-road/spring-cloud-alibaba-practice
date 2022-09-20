package com.alibaba.cloud.youxia.manager;

import com.alibaba.cloud.youxia.bo.WarehouseServiceBo;
import com.alibaba.cloud.youxia.entity.WarehouseEntity;
import com.alibaba.cloud.youxia.mapper.WarehouseMapper;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class WarehouseManager {
    @Resource
    private WarehouseMapper warehouseMapper;

    public boolean notify(WarehouseServiceBo warehouseServiceBo){
        WarehouseEntity warehouseEntity=new WarehouseEntity();
        warehouseEntity.setNum(warehouseServiceBo.getNum());
        warehouseEntity.setGoodId(warehouseServiceBo.getGoodId());
        warehouseEntity.setOrderId(warehouseServiceBo.getOrderId());
        warehouseEntity.setId(RandomUtils.nextLong());
        warehouseEntity.setWarehouseId(RandomUtils.nextLong());
        warehouseEntity.setWarehouseName("测试仓库"+RandomUtils.nextLong());
        warehouseEntity.setStatus(1);
        warehouseEntity.setGmtCreate(new Date());
        warehouseEntity.setGmtModified(new Date());
        warehouseEntity.setIsDeleted(0);
        return warehouseMapper.notify(warehouseEntity)>0?true:false;
    }
}
