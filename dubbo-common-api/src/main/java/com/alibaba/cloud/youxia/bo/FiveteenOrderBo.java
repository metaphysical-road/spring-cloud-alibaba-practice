package com.alibaba.cloud.youxia.bo;

import java.io.Serializable;
import java.math.BigDecimal;

public class FiveteenOrderBo implements Serializable {
    private Long goodId;
    private Long userId;
    private BigDecimal tradeAmount;
    private Long uuid;

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

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "FiveteenOrderBo{" +
                "goodId=" + goodId +
                ", userId=" + userId +
                ", tradeAmount=" + tradeAmount +
                ", uuid=" + uuid +
                '}';
    }
}
