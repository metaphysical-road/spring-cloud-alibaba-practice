package com.alibaba.cloud.youxia.controller;

import com.nepxion.discovery.common.constant.DiscoveryConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@RestController
@RequestMapping(value = "/order")
@ConditionalOnProperty(name = DiscoveryConstant.SPRING_APPLICATION_NAME, havingValue = "register-gray-route-order-server")
public class OrderController {

    @Autowired
    private Environment environment;

    @GetMapping(value = "/createOrder")
    public String createOrder() throws UnknownHostException {
        System.out.println(new Date().toString()+",买了一个商品！");
        StringBuilder stringBuilder=new StringBuilder();
        InetAddress inetAddress=InetAddress.getLocalHost();
        String port=environment.getProperty("server.port");
        String type=environment.getProperty("spring.application.config.path");
        stringBuilder.append(type).append(";");
        stringBuilder.append("服务提供方的IP地址为："+inetAddress.getHostAddress()+":"+port);
        return stringBuilder.toString();
    }
}
