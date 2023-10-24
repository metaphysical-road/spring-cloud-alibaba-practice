package com.alibaba.cloud.youxia.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @date: 2021/2/13 11:26 下午
 * @author: kerry
 */
//@Data
public class OrderDO {
    /**
     * 数据库主键
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 数量
     */
    private Integer amount;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 订单状态：0：创建中；1：已完结
     */
    private Integer status;

    public OrderDO(){}

    public OrderDO(String orderNo, Long userId, Long productId, Integer amount, BigDecimal money, Integer status) {
        this.orderNo=orderNo;
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
        this.money = money;
        this.status = status;
    }
}
