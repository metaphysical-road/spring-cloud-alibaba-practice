package com.alibaba.cloud.youxia.dto;

import lombok.Data;

import java.io.Serializable;

//@Data
public class UserDTO implements Serializable {
    static final long serialVersionUID = -43344454545542L;
    private Long id;
    /**
     * 租户 ID
     */
    private Long tenantId;
    private String name;
    private String addrName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }
}
