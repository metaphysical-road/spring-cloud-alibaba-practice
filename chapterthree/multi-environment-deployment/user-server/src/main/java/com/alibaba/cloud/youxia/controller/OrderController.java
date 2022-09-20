package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @DubboReference(group = "user-server",version = "1.0.0")
    private OrderService orderService;

    @GetMapping(value = "/getOrder")
    public String getOrderInfo(){
        return orderService.getOrderInfo();
    }
}
