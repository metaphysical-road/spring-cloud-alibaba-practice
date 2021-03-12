package com.alibaba.cloud.youxia.request;

import java.io.Serializable;

public class UserServiceRequest<T> implements Serializable {
    static final long serialVersionUID = -34934934939442L;

    private T requestData;

    private String productId;

    private String tenantId;

    private String uuid;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setRequestData(T requestData) {
        this.requestData = requestData;
    }

    public T getRequestData() {
        return requestData;
    }

    @Override
    public String toString() {
        return "UserServiceRequest{" + "requestData=" + requestData + ", productId='" + productId + '\''
                + ", uuid='" + uuid + ", tenantId='" + tenantId + '\'' + '}';
    }
}
