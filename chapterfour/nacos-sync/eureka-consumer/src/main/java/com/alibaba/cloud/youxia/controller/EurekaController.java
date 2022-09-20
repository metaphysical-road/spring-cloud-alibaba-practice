package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.EurekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/eureka")
public class EurekaController {

    @Autowired
    private EurekaService eurekaService;

    @GetMapping(value = "/getdata")
    @ResponseBody
    public String getData(){
        return eurekaService.hiService();
    }
}
