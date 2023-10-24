package com.alibaba.cloud.youxia.dto;

import lombok.Data;
import java.io.Serializable;

//@Data
public class GoodDTO implements Serializable {
    static final long serialVersionUID = -3434343442L;
    private Long id;
    private Long goodId;
    private String goodName;
    private Long num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
