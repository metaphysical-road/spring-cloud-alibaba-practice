package com.alibaba.cloud.youxia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;

@Configuration
public class ConfigValueTest{

    @Autowired
    private OrderService orderService;

    @Autowired
    private Config config;

    @PostConstruct
    public void init() {
        String name = orderService.getOrderInfo();
        ConfigValueThread configValueThread=new ConfigValueThread();
        configValueThread.setConfig(config);
        Executors.newCachedThreadPool().submit(configValueThread);
    }
}
