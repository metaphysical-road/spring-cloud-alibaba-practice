package com.alibaba.cloud.youxia.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Example5OrderEntity implements Serializable {
    static final long serialVersionUID = -231134345545442L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String orderName;
    private Long orderId;
}
