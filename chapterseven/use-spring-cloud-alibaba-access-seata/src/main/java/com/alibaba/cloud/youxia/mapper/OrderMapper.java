package com.alibaba.cloud.youxia.mapper;

import com.alibaba.cloud.youxia.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    Long createNewOrder(OrderEntity orderEntity);
    Integer updataOrderStatus(OrderEntity orderEntity);
}
