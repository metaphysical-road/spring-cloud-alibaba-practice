package com.alibaba.cloud.youxia.controller;

import com.nepxion.discovery.common.constant.DiscoveryConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value ="/querygood")
@ConditionalOnProperty(name = DiscoveryConstant.SPRING_APPLICATION_NAME, havingValue = "discovery-gray-route-good-service-server")
public class GoodController {

    @GetMapping(value = "/getGoodInfo")
    public String getGoodInfo(){
        System.out.println(new Date().toString()+",查询商品库存成功！");
        return "查询商品库存成功！";
    }
}
