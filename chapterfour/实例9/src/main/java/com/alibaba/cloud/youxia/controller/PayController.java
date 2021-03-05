package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.PayService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/pay")
public class PayController {

    @DubboReference(group = "example9",version = "1.0.0")
    private PayService payService;

    public String pay(){
        return payService.pay();
    }
}
