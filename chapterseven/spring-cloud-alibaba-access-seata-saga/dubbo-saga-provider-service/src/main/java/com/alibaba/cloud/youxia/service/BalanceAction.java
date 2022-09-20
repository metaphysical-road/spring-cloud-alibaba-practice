package com.alibaba.cloud.youxia.service;

import java.math.BigDecimal;
import java.util.Map;

public interface BalanceAction {
    boolean reduce(String businessKey, BigDecimal amount, Map<String, Object> params);
    boolean compensateReduce(String businessKey, Map<String, Object> params);
}
