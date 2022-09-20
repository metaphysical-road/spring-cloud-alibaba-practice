package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.SentinelDubboService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/access")
public class SentinelController {

    @DubboReference(version = "1.0.0",group = "access-sentinel-spring-cloud-alibaba-provider")
    private SentinelDubboService sentinelDubboService;

    @RequestMapping(value = "/sentinel")
    @ResponseBody
    public String testSentinel(){
        return sentinelDubboService.testSentinelFlow();
    }
}
