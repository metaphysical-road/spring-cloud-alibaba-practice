package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.bo.TccAccountServiceBo;

import java.math.BigDecimal;

public interface TccAccountService {
    boolean transformMoney(TccAccountServiceBo tccAccountServiceBo);
    void decreaseMoney(Long userId, BigDecimal money);
}
