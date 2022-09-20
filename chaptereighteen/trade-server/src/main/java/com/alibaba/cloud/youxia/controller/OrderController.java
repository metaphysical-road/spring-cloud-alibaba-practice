package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.config.TradeAlarmConfig;
import com.alibaba.cloud.youxia.service.eighteen.GoodService;
import com.alibaba.cloud.youxia.service.eighteen.PayService;
import com.alibaba.cloud.youxia.service.eighteen.TradeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/trade")
public class OrderController {

    @DubboReference(version = "1.0.0",group = "trade-server",timeout = 3000)
    private TradeService tradeService;

    @DubboReference(version = "1.0.0",group = "pay-server",timeout = 3000)
    private PayService payService;

    @Autowired
    private TradeAlarmConfig tradeAlarmConfig;

    @PostMapping(value = "/buy")
    @ResponseBody
    public boolean buy(String goodId){
        boolean tradeResult=tradeService.buy(goodId);
        try {
            Thread.sleep(Long.valueOf(tradeAlarmConfig.getDelayTime()));
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return tradeResult;
    }
    @PostMapping(value = "/pay")
    public boolean pay(Long orderId,String goodId,String amount){
        try {
            Thread.sleep(Long.valueOf(tradeAlarmConfig.getDelayTime()));
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return payService.pay(orderId,goodId,new BigDecimal(amount));
    }
}
