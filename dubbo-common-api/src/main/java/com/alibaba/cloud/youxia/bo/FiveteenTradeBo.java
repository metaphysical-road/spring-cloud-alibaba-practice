package com.alibaba.cloud.youxia.bo;

import java.io.Serializable;
import java.math.BigDecimal;

public class FiveteenTradeBo implements Serializable {
    private Long goodId;
    private Long userId;
    private BigDecimal tradeAmount;

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    @Override
    public String toString() {
        return "FiveteenTradeBo{" +
                "goodId=" + goodId +
                ", userId=" + userId +
                ", tradeAmount=" + tradeAmount +
                '}';
    }
}
