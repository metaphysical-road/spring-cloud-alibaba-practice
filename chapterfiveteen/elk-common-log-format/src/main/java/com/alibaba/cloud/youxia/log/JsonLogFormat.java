package com.alibaba.cloud.youxia.log;
import com.alibaba.cloud.youxia.log.util.JsonUtil;

public abstract class JsonLogFormat extends AbstractLogFormat {
    private static final String JSON_FORMAT = "json:";
    protected JsonLogFormat(String title) {
        super(title);
    }
    protected String format(Object obj) {
        return JSON_FORMAT + (obj == null ? "{}" : JsonUtil.objectToJSON(obj));
    }
}