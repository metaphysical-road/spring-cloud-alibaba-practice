package com.alibaba.cloud.youxia.bo;

import java.io.Serializable;

public class TccAccountServiceBo implements Serializable {
    private Long giftId;
    private Long targetUserId;
    private Long sourceUserId;

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Long getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(Long sourceUserId) {
        this.sourceUserId = sourceUserId;
    }
}
