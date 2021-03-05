package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.consumer.OrderServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("discovery")
public class OrderController {

    @Autowired
    private OrderServiceConsumer orderServiceConsumer;

    @GetMapping(value = "/getOrderName")
    public String getOrderName() {
        return orderServiceConsumer.getOrderName();
    }
}
