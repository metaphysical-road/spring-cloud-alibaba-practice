package com.alibaba.cloud.youxia.log.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class JsonUtil {

    private static final SerializerFeature[] features = {
            SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.DisableCircularReferenceDetect // http://asialee.iteye.com/blog/2101915
    };

    public static <T> Object JSONToObj(String jsonStr, Class<T> obj) {
        return JSON.parseObject(jsonStr, obj);
    }

    public static <T> String objectToJSON(T obj) {
        return JSON.toJSONString(obj, features);
    }

    public static String convMapToJSON(Map m) {
        return JSONObject.toJSONString(m, features);
    }

    public static <T> List<T> convertJsonToList(String jsonStr, Class<T> obj) {
        return JSONObject.parseArray(jsonStr, obj);
    }

    public static Map stringToCollect(String s) {
        return JSONObject.parseObject(s);
    }

    public static String convMsg(String[][] params) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < params.length; i++) {
            map.put(params[i][0], params[i][1]);
        }
        return convMapToJSON(map);

    }

    public static String convMsg(String[][] params, String message) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < params.length; i++) {
            map.put(params[i][0], params[i][1]);
        }
        map.put("message", message);
        return convMapToJSON(map);
    }

    public static String convMsg(Map<String, Object> params, String message) {
        if (params == null) {
            return "";
        }
        params.put("message", message);
        return convMapToJSON(params);
    }

}
