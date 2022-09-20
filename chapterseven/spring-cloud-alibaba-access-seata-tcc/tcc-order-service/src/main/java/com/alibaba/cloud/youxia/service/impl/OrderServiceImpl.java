package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.action.OrderTccAction;
import com.alibaba.cloud.youxia.pojo.OrderDO;
import com.alibaba.cloud.youxia.service.OrderService;
import com.alibaba.cloud.youxia.service.TccAccountService;
import com.alibaba.cloud.youxia.service.TccStorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description:
 * @date: 2021/2/13 11:23 下午
 * @author: kerry
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private  OrderTccAction orderTccAction;

    @DubboReference(version = "1.0.0",group = "SEATA_GROUP")
    private TccAccountService tccAccountService;
    @DubboReference(version = "1.0.0",group = "SEATA_GROUP")
    private TccStorageService tccStorageService;

    /**
     * 创建订单
     * @param orderDO
     */
    @GlobalTransactional
    @Override
    public void createOrder(OrderDO orderDO) {
        String orderNo=this.generateOrderNo();
        //创建订单
        orderTccAction.prepareCreateOrder(null,
                orderNo,
                orderDO.getUserId(),
                orderDO.getProductId(),
                orderDO.getAmount(),
                orderDO.getMoney());
        //扣余额
        tccAccountService.decreaseMoney(orderDO.getUserId(),orderDO.getMoney());
        //扣库存
        tccStorageService.decreaseStorage(orderDO.getProductId(),orderDO.getAmount());
    }

    private String generateOrderNo(){
        return LocalDateTime.now()
                .format(
                        DateTimeFormatter.ofPattern("yyMMddHHmmssSSS")
                );
    }
}
