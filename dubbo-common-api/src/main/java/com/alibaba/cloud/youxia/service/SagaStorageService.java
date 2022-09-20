package com.alibaba.cloud.youxia.service;

public interface SagaStorageService {
    boolean storageDeduction();
    boolean compensateStorageDeduction();
}
