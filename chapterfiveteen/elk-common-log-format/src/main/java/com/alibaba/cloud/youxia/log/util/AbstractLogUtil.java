package com.alibaba.cloud.youxia.log.util;
import com.alibaba.cloud.youxia.log.LogFormat;
import com.alibaba.cloud.youxia.log.ObjectJsonFormat;

public abstract class AbstractLogUtil {
    public static String formatLog(LogFormat logFormat) {
        return (logFormat != null) ? logFormat.log() : "";
    }
    public static String formatLog(String title, Object... message) {
        return formatLog(ObjectJsonFormat.title(title).obj(message));
    }


}