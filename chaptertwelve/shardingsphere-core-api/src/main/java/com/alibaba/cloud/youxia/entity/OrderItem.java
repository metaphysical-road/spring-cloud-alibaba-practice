package com.alibaba.cloud.youxia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
@Data
@TableName("t_order_item")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 263434701950670170L;
    private long id;
    @TableField("order_id")
    private long orderId;
    @TableField("order_item_id")
    private long orderItemId;
    @TableField("user_id")
    private long userId;
    @TableField("status")
    private Integer status;
    @TableField("good_id")
    private long goodId;
    @TableField("is_deleted")
    private Integer isDeleted;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("gmt_modified")
    private Date gmtModified;
}
