package com.alibaba.cloud.youxia.bo;

import lombok.Data;
import java.io.Serializable;

@Data
public class Example2ProductBo implements Serializable {

    static final long serialVersionUID = -278347834833442L;

    private Long id;
    private Long goodId;
    private String goodName;
    private Long num;
}
