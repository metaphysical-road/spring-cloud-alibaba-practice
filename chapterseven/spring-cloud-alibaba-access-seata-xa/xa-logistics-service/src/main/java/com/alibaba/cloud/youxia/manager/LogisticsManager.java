package com.alibaba.cloud.youxia.manager;

import com.alibaba.cloud.youxia.bo.LogisticsServiceBo;
import com.alibaba.cloud.youxia.entity.LogisticsEntity;
import com.alibaba.cloud.youxia.mapper.LogisticsMapper;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class LogisticsManager {
    @Resource
    private LogisticsMapper logisticsMapper;

    public Boolean notifyLogistics(LogisticsServiceBo logisticsServiceBo){
        LogisticsEntity logisticsEntity=new LogisticsEntity();
        logisticsEntity.setLogisticsId(RandomUtils.nextLong());
        logisticsEntity.setLogisticsName("测试"+RandomUtils.nextLong());
        logisticsEntity.setOrderId(logisticsServiceBo.getOrderId());
        logisticsEntity.setId(RandomUtils.nextLong());
        logisticsEntity.setGmtCreate(new Date());
        logisticsEntity.setGmtModified(new Date());
        logisticsEntity.setIsDeleted(0);
        logisticsEntity.setStatus(1);
        Integer result=logisticsMapper.notifyLogistics(logisticsEntity);
        return result>0?true:false;
    }
}
