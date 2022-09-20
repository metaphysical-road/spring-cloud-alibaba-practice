package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.bo.LogisticsServiceBo;
import com.alibaba.cloud.youxia.dto.LogisticsServiceDTO;

public interface XaLogisticsService {
    LogisticsServiceDTO notifyLogistics(LogisticsServiceBo logisticsServiceBo);
}
