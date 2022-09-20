package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.VerifyFlowService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyController {

    @DubboReference(version = "1.0.0",group = "verify-flow-rule-qps-thread")
    private VerifyFlowService verifyFlowService;

    @GetMapping(value = "/verify")
    public String verify(){
        return verifyFlowService.verifyFlow();
    }
}
