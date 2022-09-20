package com.alibaba.cloud.youxia.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderBo {
    private Long id;
    private String orderName;
    private Long orderId;
    private BigDecimal orderAmount;
    private int isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
}