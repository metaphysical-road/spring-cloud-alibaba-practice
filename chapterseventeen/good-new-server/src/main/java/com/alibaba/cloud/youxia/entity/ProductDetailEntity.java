package com.alibaba.cloud.youxia.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

//@Data
//@Accessors(chain = true)
@TableName(value = "product_detail")
public class ProductDetailEntity {
    //主键ID
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //商品ID
    private Long productId;
    //商品描述
    private String productDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductShortTitle() {
        return productShortTitle;
    }

    public void setProductShortTitle(String productShortTitle) {
        this.productShortTitle = productShortTitle;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

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
