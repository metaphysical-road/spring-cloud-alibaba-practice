package com.alibaba.cloud.youxia.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "example2_product")
public class Example2ProductEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long goodId;
    private String goodName;
    private Long num;
    @Version
    private Integer version;
}
