package com.alibaba.cloud.youxia.po;

import lombok.Data;

/**
 * @description:
 * @date: 2021/2/14 11:17 上午
 * @author: kerry
 */
//@Data
public class StorageDO {
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

    public Integer getResidue() {
        return residue;
    }

    public void setResidue(Integer residue) {
        this.residue = residue;
    }

    public Integer getFrozen() {
        return frozen;
    }

    public void setFrozen(Integer frozen) {
        this.frozen = frozen;
    }

    private Long id;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 剩余库存
     */
    private Integer residue;
    /**
     * TCC事务锁定的库存
     */
    private Integer frozen;
}
