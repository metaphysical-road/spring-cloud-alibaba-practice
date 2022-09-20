package com.alibaba.cloud.youxia.config;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SnowflakeInfo {
    /**
     * ip
     */
    private String ip;
    /**
     * 数据中心id
     */
    private Long dataCenterId;
    /**
     * 机器id
     */
    private Long machineId;
}
