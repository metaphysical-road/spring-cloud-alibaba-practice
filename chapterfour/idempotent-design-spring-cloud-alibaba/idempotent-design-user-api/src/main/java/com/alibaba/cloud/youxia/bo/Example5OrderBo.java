package com.alibaba.cloud.youxia.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Example5OrderBo implements Serializable {
    static final long serialVersionUID = -878347834833442L;

    private Long orderId;
    private Long id;
    private String orderName;
}
