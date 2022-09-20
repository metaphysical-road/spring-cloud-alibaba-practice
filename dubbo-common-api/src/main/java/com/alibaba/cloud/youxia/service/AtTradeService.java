package com.alibaba.cloud.youxia.service;

public interface AtTradeService {
    boolean buy(Long userId,Long goodId,Long accountId,Integer num);
}
