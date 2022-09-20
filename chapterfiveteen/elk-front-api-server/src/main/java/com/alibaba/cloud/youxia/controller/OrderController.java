package com.alibaba.cloud.youxia.controller;

import com.alibaba.cloud.youxia.bo.FiveteenTradeBo;
import com.alibaba.cloud.youxia.dto.FiveteenTradeDTO;
import com.alibaba.cloud.youxia.service.fiveteen.FiveteenTradeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/order")
@Slf4j
public class OrderController {
    @DubboReference(version = "1.0.0",group = "elk-log",timeout = 3000)
    private FiveteenTradeService fiveteenTradeService;

    @GetMapping(value = "/createOrder")
    public String getGoodInfoFromOldDataBase(){
        FiveteenTradeBo fiveteenTradeBo=new FiveteenTradeBo();
        fiveteenTradeBo.setGoodId(RandomUtils.nextLong());
        fiveteenTradeBo.setUserId(RandomUtils.nextLong());
        fiveteenTradeBo.setTradeAmount(new BigDecimal(1000));
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        FiveteenTradeDTO fiveteenTradeDTO=fiveteenTradeService.buyGoodAndCreateOrder(fiveteenTradeBo);
        log.info("API调用执行交易请求成功："+fiveteenTradeDTO.toString());
        return fiveteenTradeDTO.toString();
    }
}
