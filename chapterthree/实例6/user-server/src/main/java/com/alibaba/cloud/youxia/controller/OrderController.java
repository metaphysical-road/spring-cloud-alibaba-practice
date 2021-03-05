package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderServic;

    @GetMapping(value = "/getOrder")
    public String getOrderInfo(){
        return orderServic.getOrderInfo();
    }
}
