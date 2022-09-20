package com.alibaba.cloud.youxia.logback.appender.logger;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
public class LoggerMarkers {

    //----------------------------------------------------应用级标签----------------------------------------------------
    //超时警告
    public static final Marker TIME_OUT = MarkerFactory.getMarker("timeOut");
    //异常处理
    public static final Marker EXCEPTION_HANDLER = MarkerFactory.getMarker("exceptionHandler");


    //----------------------------------------------------业务标签----------------------------------------------------
    //日常业务
    public static final Marker BUSINESS = MarkerFactory.getMarker("business");
    //HTTP请求
    public static final Marker HTTP = MarkerFactory.getMarker("http");
}