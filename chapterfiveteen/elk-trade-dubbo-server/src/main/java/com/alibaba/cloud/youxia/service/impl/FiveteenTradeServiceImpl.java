package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.bo.FiveteenOrderBo;
import com.alibaba.cloud.youxia.bo.FiveteenTradeBo;
import com.alibaba.cloud.youxia.dto.FiveteenOrderDTO;
import com.alibaba.cloud.youxia.dto.FiveteenTradeDTO;
import com.alibaba.cloud.youxia.service.fiveteen.FiveteenOrderService;
import com.alibaba.cloud.youxia.service.fiveteen.FiveteenTradeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0",group = "elk-log",timeout = 3000)
@Slf4j
public class FiveteenTradeServiceImpl implements FiveteenTradeService {

    @DubboReference(version = "1.0.0",group = "elk-log",timeout = 3000)
    private FiveteenOrderService fiveteenOrderService;

    @Override
    public FiveteenTradeDTO buyGoodAndCreateOrder(FiveteenTradeBo fiveteenTradeBo) {
        log.info("执行交易请求："+fiveteenTradeBo.toString());
        FiveteenOrderBo fiveteenOrderBo=new FiveteenOrderBo();
        fiveteenOrderBo.setGoodId(fiveteenTradeBo.getGoodId());
        fiveteenOrderBo.setUserId(fiveteenTradeBo.getUserId());
        fiveteenOrderBo.setUuid(RandomUtils.nextLong());
        fiveteenOrderBo.setTradeAmount(fiveteenTradeBo.getTradeAmount());
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        FiveteenOrderDTO fiveteenOrderDTO=fiveteenOrderService.createOrder(fiveteenOrderBo);
        FiveteenTradeDTO result=new FiveteenTradeDTO();
        result.setGoodId(fiveteenOrderDTO.getGoodId());
        result.setOrderId(fiveteenOrderDTO.getOrderId());
        result.setUserId(fiveteenOrderDTO.getUserId());
        result.setResult(fiveteenOrderDTO.getResult());
        result.setSuccess(fiveteenOrderDTO.getSuccess());
        log.info("执行交易请求成功："+result.toString());
        return result;
    }
}
