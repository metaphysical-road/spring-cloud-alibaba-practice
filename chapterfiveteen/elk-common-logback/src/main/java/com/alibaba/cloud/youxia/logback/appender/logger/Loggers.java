package com.alibaba.cloud.youxia.logback.appender.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:booker
 * @since:2020/12/03 14:05
 * Description:获取相关的logger
 */
public class Loggers {

    private final static String EXCEPTION_LOGGER = "EXCEPTION_LOGGER";

    private final static String ALERT_LOGGER = "ALERT_LOGGER";

    private final static String DUBBO_ACCESS_LOGGER = "DUBBO_ACCESS_LOGGER";


    /**
     * 根据名称获取logger
     *
     * @param logger
     * @return
     */
    public static Logger getLogger(String logger) {
        return LoggerFactory.getLogger(logger);
    }

    /**
     * 根据类名获取logger
     *
     * @param clazz
     * @return
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * 获取root logger
     *
     * @return
     */
    public static Logger RootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    /**
     * 获取异常logger
     */
    public static Logger ExceptionLogger = LoggerFactory.getLogger(EXCEPTION_LOGGER);


    /**
     * dubbo访问日志
     */
    public static Logger DubboAccessLogger = LoggerFactory.getLogger(DUBBO_ACCESS_LOGGER);

    /**
     * 告警监控日志记录logger
     */
    public static Logger AlertLogger = LoggerFactory.getLogger(ALERT_LOGGER);
}