package com.alibaba.cloud.youxia.service;

import java.math.BigDecimal;

public interface AtOrderService {
    boolean createOrder(Long userId, BigDecimal amount, Long goodId);
}
