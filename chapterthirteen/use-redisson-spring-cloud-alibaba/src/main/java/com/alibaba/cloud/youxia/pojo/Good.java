package com.alibaba.cloud.youxia.pojo;

import java.io.Serializable;

public class Good implements Serializable {
    static final long serialVersionUID = 42L;
    private Long goodId;
    private String goodName;

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Long getGoodId() {
        return goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    @Override
    public String toString() {
        return "Good{" +
                "goodId=" + goodId +
                ", goodName='" + goodName + '\'' +
                '}';
    }
}
