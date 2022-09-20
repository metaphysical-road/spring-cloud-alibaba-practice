package com.alibaba.cloud.youxia.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0",group = "netty-spring-cloud-alibaba-push-rule")
public class RuleServiceImpl implements RuleService{
    @Override
    public String rulePush() {
        return "发布规则";
    }
}
