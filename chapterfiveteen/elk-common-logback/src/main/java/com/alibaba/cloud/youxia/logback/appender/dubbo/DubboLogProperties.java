package com.alibaba.cloud.youxia.logback.appender.dubbo;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DubboLogProperties implements Serializable {
    private static final long serialVersionUID = 2680923865439822447L;

    /**
     * 打开日志功能，默认打开
     */
    private boolean enable = true;

    /**
     * 打开调用日志记录功能
     */
    private boolean accessLogEnable = true;

    /**
     * 打开慢调用日志记录功能
     */
    private boolean slowLogEnable = true;

    /**
     * 打开异常调用日志记录功能
     */
    private boolean exceptionLogEnable = true;

    private int consumerAccessLogTime = 0;

    private int providerAccessLogTime = 0;

    private int consumerSlowLogTime = 200;

    private int providerSlowLogTime = 300;

    private List<String> deprecatedService = new ArrayList<>();

    private static DubboLogProperties instance;

    @PostConstruct
    private void init() {
        instance = this;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isAccessLogEnable() {
        return accessLogEnable;
    }

    public void setAccessLogEnable(boolean accessLogEnable) {
        this.accessLogEnable = accessLogEnable;
    }

    public boolean isSlowLogEnable() {
        return slowLogEnable;
    }

    public void setSlowLogEnable(boolean slowLogEnable) {
        this.slowLogEnable = slowLogEnable;
    }

    public int getConsumerAccessLogTime() {
        return consumerAccessLogTime;
    }

    public void setConsumerAccessLogTime(int consumerAccessLogTime) {
        this.consumerAccessLogTime = consumerAccessLogTime;
    }

    public int getProviderAccessLogTime() {
        return providerAccessLogTime;
    }

    public void setProviderAccessLogTime(int providerAccessLogTime) {
        this.providerAccessLogTime = providerAccessLogTime;
    }

    public int getConsumerSlowLogTime() {
        return consumerSlowLogTime;
    }

    public void setConsumerSlowLogTime(int consumerSlowLogTime) {
        this.consumerSlowLogTime = consumerSlowLogTime;
    }

    public int getProviderSlowLogTime() {
        return providerSlowLogTime;
    }

    public void setProviderSlowLogTime(int providerSlowLogTime) {
        this.providerSlowLogTime = providerSlowLogTime;
    }

    public boolean isExceptionLogEnable() {
        return exceptionLogEnable;
    }

    public void setExceptionLogEnable(boolean exceptionLogEnable) {
        this.exceptionLogEnable = exceptionLogEnable;
    }

    public List<String> getDeprecatedService() {
        return deprecatedService;
    }

    public void setDeprecatedService(List<String> deprecatedService) {
        this.deprecatedService = deprecatedService;
    }

    public static DubboLogProperties getInstance() {
        if (instance == null) {
            synchronized (DubboLogProperties.class) {
                if (instance == null) {
                    instance = new DubboLogProperties();
                }
            }
        }
        return instance;
    }
}