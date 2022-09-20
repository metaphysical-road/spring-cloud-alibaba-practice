package com.alibaba.cloud.youxia.bo;

import java.io.Serializable;

public class AnchorBo implements Serializable {
    private String anchorName;
    private String anchorLevel;

    public void setAnchorLevel(String anchorLevel) {
        this.anchorLevel = anchorLevel;
    }

    public void setAnchorName(String anchorName) {
        this.anchorName = anchorName;
    }

    public String getAnchorLevel() {
        return anchorLevel;
    }

    public String getAnchorName() {
        return anchorName;
    }
}
