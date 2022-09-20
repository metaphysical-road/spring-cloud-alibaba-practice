package com.alibaba.cloud.youxia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "nacosconsumer")
public class NacosConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/test")
    public String doRibbon(){
        return restTemplate.getForObject("http://EURKA-PROVIDER/data/getdata",String.class);
    }
}
