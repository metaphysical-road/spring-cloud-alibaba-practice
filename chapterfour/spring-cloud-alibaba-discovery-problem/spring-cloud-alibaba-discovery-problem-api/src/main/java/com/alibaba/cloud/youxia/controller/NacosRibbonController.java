package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.RibbonDiscoveryService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ribbon")
public class NacosRibbonController {

    @DubboReference(version = "1.0.0",group = "spring-cloud-alibaba-discovery-problem-provider",timeout = 8000)
    private RibbonDiscoveryService ribbonDiscoveryService;

    @GetMapping(value = "/test")
    public String doRibbon(){
        return ribbonDiscoveryService.getRibbonConfig();
    }
}
