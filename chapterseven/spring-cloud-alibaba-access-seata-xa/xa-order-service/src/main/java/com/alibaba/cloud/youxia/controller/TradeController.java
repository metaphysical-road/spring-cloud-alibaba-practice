package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.manager.OrderManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/order")
public class TradeController {
    @Resource
    private OrderManager orderManager;
    @GetMapping(value = "/placeOrder")
    public Map<String,String> buy(Long userId, Long goodId, Long accountId, Integer num){
        Map<String,String> executeResult=new HashMap<>();
        boolean buyResult=orderManager.placeOrder(userId,goodId,accountId,num);
        if(buyResult){
            executeResult.put("buyResult",buyResult+"");
            executeResult.put("code",200+"");
        }else{
            executeResult.put("buyResult",buyResult+"");
            executeResult.put("code",500+"");
        }
        return executeResult;
    }
}
