package com.alibaba.cloud.youxia.mapper.mybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 263434701950670170L;
    private long id;
    private long orderId;
    private long orderItemId;
    private long userId;
    private Integer status;
    private long goodId;
    private Integer isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
}
