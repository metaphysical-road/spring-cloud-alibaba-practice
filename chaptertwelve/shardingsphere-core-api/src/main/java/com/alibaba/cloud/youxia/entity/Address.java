package com.alibaba.cloud.youxia.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_address")
public class Address implements Serializable {
    private static final long serialVersionUID = 661434701950670670L;
    private Long id;
    @TableField("address_id")
    private Long addressId;
    @TableField("address_name")
    private String addressName;
    @TableField("is_deleted")
    private Integer isDeleted;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("gmt_modified")
    private Date gmtModified;
}
