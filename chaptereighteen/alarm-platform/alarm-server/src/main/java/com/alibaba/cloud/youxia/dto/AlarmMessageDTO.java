package com.alibaba.cloud.youxia.dto;

import lombok.Data;
import java.io.Serializable;
@Data
public class AlarmMessageDTO implements Serializable {

    private static final long serialVersionUID = -45345678672637597L;
    private Integer scopeId;
    private String scope;
    private String name;
    private String id0;
    private String id1;
    private String ruleName;
    private String alarmMessage;
    private Long startTime;
}
