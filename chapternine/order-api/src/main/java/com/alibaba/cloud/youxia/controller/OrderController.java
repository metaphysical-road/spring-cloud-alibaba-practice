package com.alibaba.cloud.youxia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @GetMapping(value = "/getOrderInfo")
    public String getUserInfo(HttpServletRequest request){
        String address=request.getRemoteAddr();
        Integer port=request.getRemotePort();
        return address+":"+port+" 已经通过网关路由到了订单服务";
    }
}
