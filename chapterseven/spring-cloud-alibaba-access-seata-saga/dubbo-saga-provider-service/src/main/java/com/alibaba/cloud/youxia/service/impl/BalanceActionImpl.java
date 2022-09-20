package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.service.BalanceAction;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
//@DubboService(version = "1.0.0",group="SEATA_GROUP")
@Service(value = "balanceAction")
public class BalanceActionImpl implements BalanceAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceActionImpl.class);

    //扣减
    @Override
    public boolean reduce(String businessKey, BigDecimal amount, Map<String, Object> params) {
        if(params != null && "true".equals(params.get("throwException"))){
            throw new RuntimeException("reduce balance failed");
        }
        LOGGER.info("reduce balance succeed, amount: " + amount + ", businessKey:" + businessKey);
        return true;
    }

    //补偿扣减
    @Override
    public boolean compensateReduce(String businessKey, Map<String, Object> params) {
        if(params != null && "true".equals(params.get("throwException"))){
            throw new RuntimeException("compensate reduce balance failed");
        }
        LOGGER.info("compensate reduce balance succeed, businessKey:" + businessKey);
        return true;
    }
}
