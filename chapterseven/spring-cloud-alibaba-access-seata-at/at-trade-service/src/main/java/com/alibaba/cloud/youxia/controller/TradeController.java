package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.manager.OrderManager;
import com.alibaba.cloud.youxia.service.AtTradeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/order")
public class TradeController {

    @Resource
    private OrderManager orderManager;

    @GetMapping(value = "/buy")
    public Map<String,String> buy(Long userId, Long goodId, Long accountId, Integer num){
        Map<String,String> executeResult=new HashMap<>();
        boolean buyResult=orderManager.buy(userId,goodId,accountId,num);
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
