package com.alibaba.cloud.youxia.dao;

import java.io.Serializable;

public class AccountinfoDO implements Serializable {

    static final long serialVersionUID = -23232342L;

    private Integer id;
    private String accountName;
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
