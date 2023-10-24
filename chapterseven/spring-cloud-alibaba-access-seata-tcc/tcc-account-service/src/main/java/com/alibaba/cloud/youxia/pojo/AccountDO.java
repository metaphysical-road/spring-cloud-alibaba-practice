package com.alibaba.cloud.youxia.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @date: 2021/2/13 10:25 下午
 * @author: kerry
 */
//@Data
public class AccountDO {
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 剩余可用额度
     */
    private BigDecimal residue;
    /**
     * TCC事务锁定的金额
     */
    private BigDecimal frozen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getResidue() {
        return residue;
    }

    public void setResidue(BigDecimal residue) {
        this.residue = residue;
    }

    public BigDecimal getFrozen() {
        return frozen;
    }

    public void setFrozen(BigDecimal frozen) {
        this.frozen = frozen;
    }
}
