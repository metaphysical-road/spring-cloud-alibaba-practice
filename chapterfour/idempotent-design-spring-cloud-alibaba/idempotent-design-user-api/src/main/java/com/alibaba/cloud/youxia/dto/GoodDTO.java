package com.alibaba.cloud.youxia.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class GoodDTO implements Serializable {
    static final long serialVersionUID = -3434343442L;
    private Long id;
    private Long goodId;
    private String goodName;
    private Long num;
}
