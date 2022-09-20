package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.OrderServiceImpl;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/order")
public class OrderController {

//    @DubboReference(group = "example10",version = "1.0.0")
//    private OrderServiceImpl orderService;
      @Autowired
      private OrderServiceImpl orderService;

    @GetMapping(value = "getOrderName")
    public String getOrderName() {
        return orderService.getOrderName();
    }
}
