package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.config.StarterConfig;
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
@RequestMapping(value = "/trade")
@ConditionalOnProperty(name = DiscoveryConstant.SPRING_APPLICATION_NAME, havingValue = "gray-publish-trade-server")
public class TradeController {
    @Autowired
    private StarterConfig starterConfig;
    @Autowired
    private Environment environment;
    @GetMapping(value = "/buyGood")
    public String toDoTrade() throws UnknownHostException {
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
        stringBuilder.append("交易服务提供方的IP地址为：" + inetAddress.getHostAddress() + ":" + port);
        String result=starterConfig.execute("http://gray-publish-order-server/order/createOrder");
        stringBuilder.append(result);
        return stringBuilder.toString();
    }
}
