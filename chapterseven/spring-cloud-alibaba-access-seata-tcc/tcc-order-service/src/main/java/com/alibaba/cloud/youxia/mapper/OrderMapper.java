package com.alibaba.cloud.youxia.mapper;

import com.alibaba.cloud.youxia.pojo.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * @description:
 * @date: 2021/2/14 9:30 上午
 * @author: kerry
 */
@Repository
@Mapper
public interface OrderMapper {
    /**
     * 新增
     * @return
     */
    void save(@Param("order") OrderDO order);

    /**
     * 根据id，更新状态
     * @param orderNo
     * @param status
     */
    void updateStatusByOrderNo(@Param("orderNo") String orderNo, @Param("status") Integer status);

    /**
     * 根据id，删除
     * @param orderNo
     */
    void deleteByOrderNo(@Param("orderNo")String orderNo);
}
