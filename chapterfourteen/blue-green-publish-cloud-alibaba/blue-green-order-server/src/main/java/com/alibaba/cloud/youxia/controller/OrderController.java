package com.alibaba.cloud.youxia.controller;

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
@ConditionalOnProperty(name = DiscoveryConstant.SPRING_APPLICATION_NAME, havingValue = "blue-green-order-server")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment environment;

    @GetMapping(value = "/createOrder")
    public String createOrder() throws UnknownHostException {
        System.out.println("创建订单号:" + RandomUtils.nextLong());
        System.out.println("开始调用商品服务扣减商品库存");
        System.out.println(new Date().toString() + ",买了一个商品！");
        StringBuilder stringBuilder = new StringBuilder();
        InetAddress inetAddress = InetAddress.getLocalHost();
        String port = environment.getProperty("server.port");
        String type = environment.getProperty("spring.application.config.path");
        if(environment.getProperty("spring.cloud.nacos.discovery.metadata.version").equals("1.0")){
            stringBuilder.append("订单服务路由类型：").append("blueRoute").append(";");
        }else if(environment.getProperty("spring.cloud.nacos.discovery.metadata.version").equals("1.1")){
            stringBuilder.append("订单服务路由类型：").append("greenRoute").append(";");
        }else if(environment.getProperty("spring.cloud.nacos.discovery.metadata.version").equals("1.2")){
            stringBuilder.append("订单服务路由类型：").append("defaultRoute").append(";");
        }
        stringBuilder.append(type).append(";");
        stringBuilder.append("订单服务提供方的IP地址为：" + inetAddress.getHostAddress() + ":" + port);
        HttpHeaders headers = new HttpHeaders();
        headers.add("a","1");
        headers.add("b","3");
        ResponseEntity<String> result = restTemplate.exchange("http://blue-green-good-server/good/deductingInventory", HttpMethod.GET,
                new HttpEntity<String>(null,headers),String.class);
        stringBuilder.append(result.getBody());
        return stringBuilder.toString();
    }
}
