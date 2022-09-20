package com.alibaba.cloud.youxia.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName(value = "product_detail")
public class ProductDetailEntity {
    //主键ID
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //商品ID
    private Long productId;
    //商品描述
    private String productDesc;
    //商品标题
    private String productTitle;
    //商品短标题
    private String productShortTitle;
    //商品内容
    private String productContent;
    // 1 表示删除，0 表示未删除
    private Integer isDeleted;
    //创建时间
    private Date gmtCreate;
    //更新时间
    private Date gmtModified;
    //版本号
    @Version
    private Integer version;
}
