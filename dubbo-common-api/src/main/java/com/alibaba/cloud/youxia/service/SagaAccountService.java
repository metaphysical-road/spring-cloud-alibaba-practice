package com.alibaba.cloud.youxia.service;

public interface SagaAccountService {
    boolean accountDeduction();
    boolean compensateAccountDeduction();
}
