package com.alibaba.cloud.youxia.bo;

import lombok.Data;
import java.io.Serializable;

//@Data
public class Example2ProductBo implements Serializable {

    static final long serialVersionUID = -278347834833442L;

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
