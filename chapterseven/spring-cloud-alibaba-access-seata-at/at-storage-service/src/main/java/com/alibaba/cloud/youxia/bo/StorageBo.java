package com.alibaba.cloud.youxia.bo;

import lombok.Data;
//@Data
public class StorageBo {
    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    Long goodId;
    Integer num;
}
