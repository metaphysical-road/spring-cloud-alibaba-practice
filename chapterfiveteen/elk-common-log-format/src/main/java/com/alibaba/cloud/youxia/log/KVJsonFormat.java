package com.alibaba.cloud.youxia.log;
import java.util.LinkedHashMap;
import java.util.Map;

public class KVJsonFormat extends JsonLogFormat {
    private Map<String, Object> map = new LinkedHashMap<>();
    private KVJsonFormat(String title) {
        super(title);
    }
    public static KVJsonFormat title(String title) {
        return new KVJsonFormat(title);
    }
    public KVJsonFormat add(String key, Object v) {
        map.put(key, v);
        return this;
    }
    public KVJsonFormat addAll(Map<String, Object> params) {
        if (params != null) {
            this.map.putAll(params);
        }
        return this;
    }
    @Override
    protected String buildLogMsg() {
        return format(map);
    }
}