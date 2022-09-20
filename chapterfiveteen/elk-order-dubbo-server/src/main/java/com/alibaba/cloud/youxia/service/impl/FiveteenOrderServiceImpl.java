package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.bo.FiveteenOrderBo;
import com.alibaba.cloud.youxia.dto.FiveteenOrderDTO;
import com.alibaba.cloud.youxia.service.fiveteen.FiveteenOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0",group = "elk-log",timeout = 3000)
@Slf4j
public class FiveteenOrderServiceImpl implements FiveteenOrderService {
    @Override
    public FiveteenOrderDTO createOrder(FiveteenOrderBo fiveteenOrderBo) {
        log.info("创建订单：" + fiveteenOrderBo.toString());
        FiveteenOrderDTO result = new FiveteenOrderDTO();
        result.setOrderId(RandomUtils.nextLong());
        result.setGoodId(fiveteenOrderBo.getGoodId());
        result.setUserId(fiveteenOrderBo.getUserId());
        result.setSuccess(true);
        result.setResult("创建订单成功！");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        log.info("创建订单成功：" + result.toString());
        return result;
    }
}
