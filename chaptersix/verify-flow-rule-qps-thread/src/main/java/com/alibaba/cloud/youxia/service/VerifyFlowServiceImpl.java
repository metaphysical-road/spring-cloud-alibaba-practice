package com.alibaba.cloud.youxia.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0",group ="verify-flow-rule-qps-thread")
public class VerifyFlowServiceImpl implements VerifyFlowService{
    @Override
    public String verifyFlow() {
        return "verify";
    }
}
