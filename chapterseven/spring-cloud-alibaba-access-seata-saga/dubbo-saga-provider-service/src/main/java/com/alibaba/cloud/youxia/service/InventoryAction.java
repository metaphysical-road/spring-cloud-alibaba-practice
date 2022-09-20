package com.alibaba.cloud.youxia.service;

public interface InventoryAction {
    boolean reduce(String businessKey, int count);
    boolean compensateReduce(String businessKey);
}
