package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.PersistenceService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class VerifyPersistenceController {

    @DubboReference(version = "1.0.0",group = "verify-persistence-rule")
    private PersistenceService persistenceService;

    @GetMapping("/persistence")
    @SentinelResource("resource")
    @ResponseBody
    public String persistence() {
        return persistenceService.persistenceTest();
    }

    @GetMapping("/getPersistenceInfo")
    @SentinelResource("resource")
    @ResponseBody
    public String getPersistenceInfo() {
        return persistenceService.persistenceTest();
    }
}
