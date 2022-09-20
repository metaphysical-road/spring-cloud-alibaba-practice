package com.alibaba.cloud.youxia.manager;

import com.alibaba.cloud.youxia.bo.LogisticsServiceBo;
import com.alibaba.cloud.youxia.bo.WarehouseServiceBo;
import com.alibaba.cloud.youxia.dto.LogisticsServiceDTO;
import com.alibaba.cloud.youxia.dto.WarehouseServiceDTO;
import com.alibaba.cloud.youxia.entity.OrderEntity;
import com.alibaba.cloud.youxia.mapper.OrderMapper;
import com.alibaba.cloud.youxia.service.XaLogisticsService;
import com.alibaba.cloud.youxia.service.XaWarehouseService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.RandomUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderManager {
    class Result{
        private Long id;
        private boolean result;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }
    }

    @Resource
    private OrderMapper orderMapper;

    @DubboReference(version = "1.0.0",group = "SEATA_GROUP")
    private XaWarehouseService xaWarehouseService;

    @DubboReference(version = "1.0.0",group = "SEATA_GROUP")
    private XaLogisticsService xaLogisticsService;

    public Result createOrder(Long userId, BigDecimal amount, Long goodId) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(RandomUtils.nextLong());
        orderEntity.setId(RandomUtils.nextLong());
        orderEntity.setOrderAmount(amount);
        orderEntity.setOrderName("测试订单" + RandomUtils.nextInt());
        orderEntity.setGoodId(goodId);
        orderEntity.setUserId(userId);
        orderEntity.setIsDeleted(0);
        orderEntity.setGmtCreate(new Date());
        orderEntity.setGmtModified(new Date());
        Long result = orderMapper.createNewOrder(orderEntity);
        Result createResult = new Result();
        createResult.setResult(true ? result > 0 : false);
        createResult.setId(orderEntity.getId());
        return createResult;
    }

    public boolean updataOrderStatus(Long id,Integer orderStatus) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(id);
        orderEntity.setOrderStatus(orderStatus);
        Integer result = orderMapper.updataOrderStatus(orderEntity);
        return true ? result > 0 : false;
    }

    @GlobalTransactional(name = "pay-order", rollbackFor = Exception.class)
    public boolean placeOrder(Long userId, Long goodId, Long accountId, Integer num) {
        Result orderResult = createOrder(userId, new BigDecimal(100), goodId);
        WarehouseServiceBo warehouseServiceBo = new WarehouseServiceBo();
        warehouseServiceBo.setOrderId(orderResult.getId());
        warehouseServiceBo.setGoodId(goodId);
        warehouseServiceBo.setNum(num);
        WarehouseServiceDTO warehouseServiceDTO = xaWarehouseService.notifyWarehouse(warehouseServiceBo);
        LogisticsServiceBo logisticsServiceBo = new LogisticsServiceBo();
        logisticsServiceBo.setOrderId(orderResult.getId());
        LogisticsServiceDTO logisticsServiceDTO = xaLogisticsService.notifyLogistics(logisticsServiceBo);
        if (orderResult.isResult() && warehouseServiceDTO.getStatus() == 200 && logisticsServiceDTO.getStatus() == 200) {
            boolean updataOrderStatusResult = updataOrderStatus(orderResult.getId(), 1);
            if (updataOrderStatusResult) {
                return true;
            }
            return false;
        }
        return true;
    }
}
