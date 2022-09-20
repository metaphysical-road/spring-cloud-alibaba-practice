package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.bo.LogisticsServiceBo;
import com.alibaba.cloud.youxia.dto.LogisticsServiceDTO;
import com.alibaba.cloud.youxia.manager.LogisticsManager;
import com.alibaba.cloud.youxia.service.XaLogisticsService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(version = "1.0.0",group = "SEATA_GROUP")
public class XaLogisticsServiceImpl implements XaLogisticsService {
    @Resource
    private LogisticsManager logisticsManager;

    @Override
    public LogisticsServiceDTO notifyLogistics(LogisticsServiceBo logisticsServiceBo) {
        Boolean result = logisticsManager.notifyLogistics(logisticsServiceBo);
        LogisticsServiceDTO logisticsServiceDTO = new LogisticsServiceDTO();
        if (result) {
            logisticsServiceDTO.setStatus(200);
        } else {
            logisticsServiceDTO.setStatus(500);
        }
        return logisticsServiceDTO;
    }
}
