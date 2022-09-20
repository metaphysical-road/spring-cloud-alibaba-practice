package com.alibaba.cloud.youxia.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
@Data
@TableName("t_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 661434701950670670L;
    private Long id;
    @TableField("user_id")
    private Long userId;
    @TableField("order_name")
    private String orderName;
    @TableField("address_id")
    private Long addressId;
    @TableField("status")
    private Integer status;
    @TableField("order_id")
    private long orderId;
    @TableField("is_deleted")
    private Integer isDeleted;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("gmt_modified")
    private Date gmtModified;
}
