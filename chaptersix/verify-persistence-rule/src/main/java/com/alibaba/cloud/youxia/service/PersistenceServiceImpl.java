package com.alibaba.cloud.youxia.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "verify-persistence-rule",version = "1.0.0")
public class PersistenceServiceImpl implements PersistenceService{
    @Override
    public String persistenceTest() {
        return "验证持久化";
    }
}
