package com.alibaba.cloud.youxia.dto;
import lombok.Data;

/**
 * @author wanzepeng
 */
@Data
public class QueueStatInfo {
    private String brokerName;
    private int queueId;
    private long brokerOffset;
    private long consumerOffset;
    private String clientInfo;
    private long lastTimestamp;
}
