package com.alibaba.cloud.youxia.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

//@Data
//@Accessors(chain = true)
@TableName(value = "anchor")
public class AnchorEntity implements Serializable {
    static final long serialVersionUID = -232434345545442L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnchorName() {
        return anchorName;
    }

    public void setAnchorName(String anchorName) {
        this.anchorName = anchorName;
    }

    public String getAnchorLevel() {
        return anchorLevel;
    }

    public void setAnchorLevel(String anchorLevel) {
        this.anchorLevel = anchorLevel;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    private String anchorName;
    private String anchorLevel;
    @Version
    private Integer version;
}
