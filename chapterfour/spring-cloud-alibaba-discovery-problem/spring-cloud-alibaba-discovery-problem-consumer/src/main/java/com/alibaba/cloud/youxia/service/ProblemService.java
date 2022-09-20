package com.alibaba.cloud.youxia.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {
    @DubboReference(version = "1.0.0",group = "spring-cloud-alibaba-discovery-problem-provider",timeout = 8000)
    private RibbonDiscoveryService ribbonDiscoveryService;

    public String getConfig(){
        return ribbonDiscoveryService.getRibbonConfig();
    }
}
