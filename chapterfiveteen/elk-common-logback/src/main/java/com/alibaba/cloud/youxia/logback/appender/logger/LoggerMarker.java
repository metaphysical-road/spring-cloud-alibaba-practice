package com.alibaba.cloud.youxia.logback.appender.logger;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
public class LoggerMarker {
    public final static String DUBBO_ACCESS_LOG = "dubbo_access_log";
    public final static String DUBBO_EXCEPTION_LOG = "dubbo_exception_log";
    public final static String DUBBO_DEPRECATED_LOG = "dubbo_deprecated_log";
    public final static String DUBBO_SLOW_LOG = "dubbo_slow_log";

    public static Marker getMarker(String name) {
        return MarkerFactory.getMarker("[" + name + "]。");
    }
}