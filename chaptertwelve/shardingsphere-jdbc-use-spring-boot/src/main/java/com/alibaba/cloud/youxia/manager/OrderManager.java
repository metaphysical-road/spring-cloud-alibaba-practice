package com.alibaba.cloud.youxia.manager;
import com.alibaba.cloud.youxia.config.SnowFlakeService;
import com.alibaba.cloud.youxia.entity.Address;
import com.alibaba.cloud.youxia.entity.Order;
import com.alibaba.cloud.youxia.entity.OrderItem;
import com.alibaba.cloud.youxia.mapper.AddressMapper;
import com.alibaba.cloud.youxia.mapper.OrderItemMapper;
import com.alibaba.cloud.youxia.mapper.OrderMapper;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class OrderManager {
    @Resource
    private SnowFlakeService snowFlakeService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private AddressMapper addressMapper;
    public synchronized Integer insertOrderInfo() {
        final Order orderEntity = new Order();
        orderEntity.setOrderName("test"+ snowFlakeService.nextId());
        orderEntity.setId(snowFlakeService.nextId()+RandomUtils.nextInt(1,3));
        orderEntity.setGmtCreate(new Date());
        orderEntity.setGmtModified(new Date());
        orderEntity.setOrderId(snowFlakeService.nextId()+RandomUtils.nextInt(1,3));
        orderEntity.setIsDeleted(0);
        orderEntity.setAddressId(snowFlakeService.nextId()+RandomUtils.nextInt(1,3));
        orderEntity.setUserId(snowFlakeService.nextId()+RandomUtils.nextInt(1,3));
        orderEntity.setStatus(0);
        Integer orderResult = orderMapper.insert(orderEntity);
        final OrderItem orderItem = new OrderItem();
        orderItem.setId(snowFlakeService.nextId()+RandomUtils.nextInt(1,3));
        orderItem.setGmtCreate(new Date());
        orderItem.setGmtModified(new Date());
        orderItem.setOrderItemId(snowFlakeService.nextId()+RandomUtils.nextInt(1,3));
        orderItem.setUserId(orderEntity.getUserId());
        orderItem.setIsDeleted(0);
        orderItem.setOrderId(orderEntity.getOrderId());
        orderItem.setGoodId(snowFlakeService.nextId()+RandomUtils.nextInt(1,3));
        orderItem.setStatus(0);
        Integer orderItemResult = orderItemMapper.insert(orderItem);
        final Address address=new Address();
        address.setId(snowFlakeService.nextId()+RandomUtils.nextInt(1,3));
        address.setAddressId(orderEntity.getAddressId());
        address.setAddressName("test-address"+snowFlakeService.nextId());
        address.setGmtCreate(new Date());
        address.setGmtModified(new Date());
        address.setIsDeleted(0);
        Integer addressInsertResult=addressMapper.insert(address);
        return orderResult+orderItemResult+addressInsertResult;
    }

    public synchronized List<Order> selectOrderInfo() {
        List<Order> itemList=orderMapper.selectList(null);
        return itemList;
    }
}
