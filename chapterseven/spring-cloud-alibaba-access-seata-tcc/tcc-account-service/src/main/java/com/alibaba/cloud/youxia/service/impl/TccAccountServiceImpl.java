package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.action.AccountTccAction;
import com.alibaba.cloud.youxia.bo.TccAccountServiceBo;
import com.alibaba.cloud.youxia.service.TccAccountService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.math.BigDecimal;

@DubboService(version = "1.0.0",group = "SEATA_GROUP")
public class TccAccountServiceImpl implements TccAccountService {

    @Resource
    private AccountTccAction accountTccAction;

    @Override
    public boolean transformMoney(TccAccountServiceBo tccAccountServiceBo) {
        return false;
    }

    @Override
    public void decreaseMoney(Long userId, BigDecimal money) {
        accountTccAction.prepareDecreaseMoney(null, userId, money);
    }
}
