package com.alibaba.cloud.youxia.log;
import com.alibaba.cloud.youxia.log.util.JsonUtil;
public class ObjectJsonFormat extends AbstractLogFormat {
    private Object[] obj;
    private ObjectJsonFormat(String title) {
        super(title);
    }

    public static ObjectJsonFormat title(String title) {
        return new ObjectJsonFormat(title);
    }

    public ObjectJsonFormat obj(Object... obj) {
        this.obj = obj;
        return this;
    }
    @Override
    protected String buildLogMsg() {
        return JsonUtil.objectToJSON(obj);
    }
}