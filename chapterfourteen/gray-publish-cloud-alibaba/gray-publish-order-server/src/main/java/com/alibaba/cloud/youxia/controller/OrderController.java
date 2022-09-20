package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.config.StarterConfig;
import com.nepxion.discovery.common.constant.DiscoveryConstant;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@RestController
@RequestMapping(value = "/order")
@ConditionalOnProperty(name = DiscoveryConstant.SPRING_APPLICATION_NAME, havingValue = "gray-publish-order-server")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment environment;
    @Autowired
    private StarterConfig starterConfig;

    @GetMapping(value = "/createOrder")
    public String createOrder() throws UnknownHostException {
        System.out.println("创建订单号:" + RandomUtils.nextLong());
        System.out.println("开始调用商品服务扣减商品库存");
        System.out.println(new Date().toString() + ",买了一个商品！");
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
        stringBuilder.append("订单服务提供方的IP地址为：" + inetAddress.getHostAddress() + ":" + port);
        String result=starterConfig.execute("http://gray-publish-good-server/good/deductingInventory");
        stringBuilder.append(result);
        return stringBuilder.toString();
    }
}
