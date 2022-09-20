package com.alibaba.cloud.youxia.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    static final long serialVersionUID = -43344454545542L;
    private Long id;
    /**
     * 租户 ID
     */
    private Long tenantId;
    private String name;
    private String addrName;
}
