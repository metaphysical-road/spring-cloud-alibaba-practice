package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.ProblemService;
import com.alibaba.cloud.youxia.service.RibbonDiscoveryService;
import com.alibaba.cloud.youxia.service.RibbonTestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/ribbon")
public class NacosRibbonController {
    @Resource
    private ProblemService problemService;
    @GetMapping(value = "/test")
    public String doRibbon(){
        return problemService.getConfig();
    }
}
