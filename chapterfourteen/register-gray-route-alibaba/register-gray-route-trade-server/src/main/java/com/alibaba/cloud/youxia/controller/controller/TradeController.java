package com.alibaba.cloud.youxia.controller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
@RequestMapping(value = "/trade")
public class TradeController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/buyGood")
    public String buyGood(){
        String result=restTemplate.getForObject("http://register-gray-route-order-server/order/createOrder", String.class);
        return result;
    }
}
