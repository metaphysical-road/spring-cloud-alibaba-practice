package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.bo.WarehouseServiceBo;
import com.alibaba.cloud.youxia.dto.WarehouseServiceDTO;

public interface XaWarehouseService {
    WarehouseServiceDTO notifyWarehouse(WarehouseServiceBo warehouseServiceBo);
}
