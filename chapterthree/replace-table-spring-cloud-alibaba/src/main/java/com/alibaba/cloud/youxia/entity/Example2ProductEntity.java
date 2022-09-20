package com.alibaba.cloud.youxia.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName(value = "example2_product")
public class Example2ProductEntity implements Serializable {
    static final long serialVersionUID = -232434345545442L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long goodId;
    private String goodName;
    private Long num;
    @Version
    private Integer version;
}
