package com.alibaba.cloud.youxia.service;

public interface TccStorageService {
    void decreaseStorage(Long productId, Integer count);
}
