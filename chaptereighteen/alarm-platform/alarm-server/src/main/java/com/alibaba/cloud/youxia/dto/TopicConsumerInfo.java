package com.alibaba.cloud.youxia.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import java.util.List;

//@Data
public class TopicConsumerInfo {
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getDiffTotal() {
        return diffTotal;
    }

    public void setDiffTotal(long diffTotal) {
        this.diffTotal = diffTotal;
    }

    public long getLastTimestamp() {
        return lastTimestamp;
    }

    public void setLastTimestamp(long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }

    public List<QueueStatInfo> getQueueStatInfoList() {
        return queueStatInfoList;
    }

    public void setQueueStatInfoList(List<QueueStatInfo> queueStatInfoList) {
        this.queueStatInfoList = queueStatInfoList;
    }

    private long diffTotal;
    private long lastTimestamp;
    private List<QueueStatInfo> queueStatInfoList = Lists.newArrayList();
}
