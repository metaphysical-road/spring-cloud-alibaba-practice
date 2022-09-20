package com.alibaba.cloud.youxia.util;

import lombok.Data;
import java.io.Serializable;

public class RestResult implements Serializable {
    private static final long serialVersionUID = 8218042700637819800L;
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
