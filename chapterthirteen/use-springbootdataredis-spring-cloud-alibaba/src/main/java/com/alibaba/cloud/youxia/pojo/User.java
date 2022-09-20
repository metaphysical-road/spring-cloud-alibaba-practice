package com.alibaba.cloud.youxia.pojo;

import java.io.Serializable;

public class User implements Serializable {
    static final long serialVersionUID = -343434343442L;
    private Long userId;
    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
