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

@RestController
@RequestMapping(value = "/logistics")
@ConditionalOnProperty(name = DiscoveryConstant.SPRING_APPLICATION_NAME, havingValue = "gray-publish-logistics-server")
public class LogisticsController {
    @Autowired
    private Environment environment;
    @GetMapping(value = "/createLogisticsOrder")
    public String createLogisticsOrder() throws UnknownHostException {
        System.out.println("开始创建物流订单");
        StringBuilder stringBuilder = new StringBuilder();
        InetAddress inetAddress = InetAddress.getLocalHost();
        String port = environment.getProperty("server.port");
        String type = environment.getProperty("spring.application.config.path");
        String applicationName=environment.getProperty("spring.application.name");
        if(environment.getProperty("spring.cloud.nacos.discovery.metadata.version").equals("1.0")){
            stringBuilder.append("交易服务路由类型：").append(applicationName).append(",").append("StableRoute").append(";");
        }else if(environment.getProperty("spring.cloud.nacos.discovery.metadata.version").equals("1.1")){
            stringBuilder.append("交易服务路由类型：").append(applicationName).append(",").append("GrayRoute").append(";");
        }
        stringBuilder.append(type).append(";");
        stringBuilder.append("物流服务提供方的IP地址为：" + inetAddress.getHostAddress() + ":" + port);
        return stringBuilder.toString();
    }
}
