package com.alibaba.cloud.youxia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户实体对应表 user
 * </p>
 *
 */
@Data
@Accessors(chain = true)
public class UserEntity {
    private Long id;
    /**
     * 租户 ID
     */
    private Long tenantId;
    private String name;

    @TableField(exist = false)
    private String addrName;

}
