package com.alibaba.cloud.youxia.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {
    static final long serialVersionUID = -233434433442L;
    private Long orderId;
    private String orderName;
    private BigDecimal orderAmount;

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", orderAmount=" + orderAmount +
                '}';
    }
}
