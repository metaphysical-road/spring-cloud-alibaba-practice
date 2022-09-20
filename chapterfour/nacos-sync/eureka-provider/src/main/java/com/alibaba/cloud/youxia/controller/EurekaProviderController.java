package com.alibaba.cloud.youxia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/data")
public class EurekaProviderController {
    @GetMapping(value = "/getdata")
    @ResponseBody
    public String getData(){
        System.out.println("通过Eureka注册中心暴露的Restful API服务！");
        return "调用接口成功！";
    }
}
