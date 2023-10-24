package com.alibaba.cloud.youxia.bo;

import lombok.Data;

import java.io.Serializable;

//@Data
public class Example5OrderBo implements Serializable {
    static final long serialVersionUID = -878347834833442L;

    private Long orderId;
    private Long id;
    private String orderName;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
