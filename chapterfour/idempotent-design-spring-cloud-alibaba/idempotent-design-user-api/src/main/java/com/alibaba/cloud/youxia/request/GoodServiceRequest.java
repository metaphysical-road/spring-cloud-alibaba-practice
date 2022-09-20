package com.alibaba.cloud.youxia.request;

import java.io.Serializable;

public class GoodServiceRequest<T> implements Serializable {

    private T requestData;

    private String productId;

    private String tenantId;

    private String uuid;

    private String threadId;

    private String userName;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setRequestData(T requestData) {
        this.requestData = requestData;
    }

    public T getRequestData() {
        return requestData;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getThreadId() {
        return threadId;
    }
}
