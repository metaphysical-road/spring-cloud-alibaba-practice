package com.alibaba.cloud.youxia.controller;

import com.nepxion.discovery.common.constant.DiscoveryConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@RestController
@RequestMapping(value ="/tradeManager")
@ConditionalOnProperty(name = DiscoveryConstant.SPRING_APPLICATION_NAME, havingValue = "discovery-gray-route-trade-service-server")
public class TradeController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @GetMapping(value = "/buygood")
    public String buyGoods() throws UnknownHostException {
        System.out.println(new Date().toString()+",买了一个商品！");
        StringBuilder stringBuilder=new StringBuilder();
        InetAddress inetAddress=InetAddress.getLocalHost();
        String port=environment.getProperty("server.port");
        String type=environment.getProperty("spring.application.config.path");
        stringBuilder.append(type).append(";");
        stringBuilder.append("服务提供方的IP地址为："+inetAddress.getHostAddress()+":"+port);
        String result=restTemplate.getForObject("http://discovery-gray-route-good-service-server/querygood/getGoodInfo", String.class);
        stringBuilder.append(";").append("结果为：").append(result);
        return stringBuilder.toString();
    }
}
