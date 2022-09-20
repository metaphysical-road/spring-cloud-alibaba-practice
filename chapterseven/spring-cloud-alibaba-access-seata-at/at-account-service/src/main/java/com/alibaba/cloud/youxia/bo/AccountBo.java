package com.alibaba.cloud.youxia.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AccountBo {
    private Long id;
    private String accountName;
    private Long accountId;
    private Long userId;
    private BigDecimal amount;
    private Date gmtModified;
    private Integer isDeleted;
}
