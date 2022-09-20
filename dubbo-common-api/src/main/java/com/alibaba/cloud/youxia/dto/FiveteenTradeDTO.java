package com.alibaba.cloud.youxia.dto;

import java.io.Serializable;

public class FiveteenTradeDTO implements Serializable {
    private Boolean isSuccess;
    private String result;
    private Long orderId;
    private Long userId;
    private Long goodId;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    @Override
    public String toString() {
        return "FiveteenTradeDTO{" +
                "isSuccess=" + isSuccess +
                ", result='" + result + '\'' +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", goodId=" + goodId +
                '}';
    }
}
