package com.alibaba.cloud.youxia.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * ORM中的实体对象
 */
@Data
@Accessors(chain = true)
@TableName(value = "example2_product")
public class Example2ProductEntity implements Serializable {
    static final long serialVersionUID = -232434345545442L;
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商品ID
     */
    private Long goodId;
    /**
     * 商品名称
     */
    private String goodName;
    /**
     * 商品库存
     */
    private Long num;
    /**
     * 1.乐观锁的版本号
     * 2.如果注释掉这一行代码，则乐观锁失效
     * 3.需要在数据库表中，添加一个version字段
     */
    @Version
    private Integer version;
}
