package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.RibbonDiscoveryService;
import com.alibaba.cloud.youxia.service.RibbonTestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/ribbon")
public class NacosRibbonController {

    @DubboReference(version = "1.0.0",group = "ribbon-provider",timeout = 8000)
    private RibbonDiscoveryService ribbonDiscoveryService;

    @DubboReference
    private RibbonTestService ribbonTestService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/test")
    public String doRibbon(){
        return restTemplate.getForObject("http://ribbon-discovery-spring-cloud-alibaba-provider/provider/getRibbonConfig",String.class);
    }
}
