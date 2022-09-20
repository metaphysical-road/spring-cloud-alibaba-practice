package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.ConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
@RequestMapping(value = "/skywalking")
public class SkywalkingController {

    @Resource
    private ConsumerService consumerService;

    @GetMapping(value = "")
    public String skywalking(){
        return consumerService.consumer();
    }
}
