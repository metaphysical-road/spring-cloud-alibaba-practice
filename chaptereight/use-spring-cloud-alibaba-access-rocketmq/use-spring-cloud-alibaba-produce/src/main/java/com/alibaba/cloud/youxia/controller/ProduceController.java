package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.config.RocketmqConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produce")
public class ProduceController {
    @Autowired
    private RocketmqConfig rocketmqConfig;

    @GetMapping(value = "/message")
    public String produce(){
        rocketmqConfig.startRunSendMessage();
        return "success";
    }
}
