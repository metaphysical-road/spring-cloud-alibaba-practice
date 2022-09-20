package com.alibaba.cloud.youxia.logback.appender.dubbo;
import java.io.Serializable;
public class LogInfo implements Serializable {
    private static final long serialVersionUID = 2680923865439822449L;

//    /**
//     * 调用链ID
//     */
//    private String traceId;

    /**
     * 表明这条日志是provider端还是consumer端的
     */
    private String side;

    /**
     * 服务提供者主机ip
     */
    private String providerIp;

    /**
     * 服务消费者主机名
     */
    private String consumerIp;

    /**
     * 服务名称
     */
    private String service;

    /**
     * 服务方法
     */
    private String method;

    /**
     * 执行时间
     */
    private Long time;

    /**
     * 调用信息
     */
    private String invoke;

    /**
     * 返回结果
     */
    private String response;

//    public String getTraceId() {
//        return traceId;
//    }
//
//    public void setTraceId(String traceId) {
//        this.traceId = traceId;
//    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getProviderIp() {
        return providerIp;
    }

    public void setProviderIp(String providerIp) {
        this.providerIp = providerIp;
    }

    public String getConsumerIp() {
        return consumerIp;
    }

    public void setConsumerIp(String consumerIp) {
        this.consumerIp = consumerIp;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getInvoke() {
        return invoke;
    }

    public void setInvoke(String invoke) {
        this.invoke = invoke;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}