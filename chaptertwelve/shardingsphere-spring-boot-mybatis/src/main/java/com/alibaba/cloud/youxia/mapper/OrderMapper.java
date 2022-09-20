package com.alibaba.cloud.youxia.mapper;

import com.alibaba.cloud.youxia.mapper.mybatis.entity.Order;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface OrderMapper {
    Integer insertOrder(Order order);
}
