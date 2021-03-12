package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.service.impl.CacheGuavaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController(value = "/getOrder")
public class OrderController {

    @Autowired
    private CacheGuavaServiceImpl cacheGuavaService;

    @GetMapping(value = "/test")
    public Map<String, String> testGuava(){
        Map<String, String> result=new HashMap<>(8);
        String s=cacheGuavaService.executeSeckill();
        result.put("result",s);
        return result;
    }
}
