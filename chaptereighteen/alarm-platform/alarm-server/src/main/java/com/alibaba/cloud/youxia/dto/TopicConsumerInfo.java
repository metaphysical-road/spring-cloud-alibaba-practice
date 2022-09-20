package com.alibaba.cloud.youxia.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import java.util.List;

@Data
public class TopicConsumerInfo {
    private String topic;
    private long diffTotal;
    private long lastTimestamp;
    private List<QueueStatInfo> queueStatInfoList = Lists.newArrayList();
}
