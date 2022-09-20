package com.alibaba.cloud.youxia.service;

public interface SagaOrderService {
    boolean createOrder();
    boolean compensateCreateOrder();
}
