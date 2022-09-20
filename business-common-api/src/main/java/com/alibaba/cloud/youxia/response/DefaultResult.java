/*
 * Copyright (c) 2001-2020 melotgroup Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with http://melotgroup.com/.
 */
package com.alibaba.cloud.youxia.response;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DefaultResult<T> implements Result<T>, Serializable {

    private static final long serialVersionUID = -4408341719434417427L;
    public static final String SUCCESS_CODE = "0";
    public static final String UNKNOWN_ERROR = "1";
    public static final String ERROR_PREFIX = "SYS_";

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 响应数据
     */
    @Valid
    private T data;

    /**
     * 异常类名
     */
    private String errorClass;

    /**
     * 详细异常信息
     */
    private String errorStack;

    /**
     * 参数校验错误信息
     */
    private List<ViolationItem> violationItems;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public List<ViolationItem> getViolationItems() {
        return violationItems;
    }

    @Override
    public String getErrorClass() {
        return errorClass;
    }

    @Override
    public String getErrorStack() {
        return errorStack;
    }

    @Override
    public DefaultResult<T> setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public DefaultResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public DefaultResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public DefaultResult<T> setViolationItems(List<ViolationItem> violationItems) {
        this.violationItems = violationItems;
        return this;
    }

    @Override
    public DefaultResult<T> setErrorClass(String errorClass) {
        this.errorClass = errorClass;
        return this;
    }

    @Override
    public DefaultResult<T> setErrorStack(String errorStack) {
        this.errorStack = errorStack;
        return this;
    }

    @Override
    public DefaultResult<T> addViolationItem(String field, String message) {
        if (violationItems == null) {
            violationItems = new ArrayList<>();
        }
        violationItems.add(new DefaultViolationItem(field, message));
        return this;
    }

    @Override
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }

    @Override
    public boolean isError() {
        return UNKNOWN_ERROR.equals(code) || (code != null && code.startsWith(ERROR_PREFIX));
    }

    @Override
    public boolean isFailure() {
        return (!isSuccess()) && (!isError());
    }

    /**
     * ViolationItem的实现
     *
     * @author freeway
     */
    public static class DefaultViolationItem implements ViolationItem, Serializable {

        private static final long serialVersionUID = 2803300694383082237L;

        private String field;
        private String message;

        public DefaultViolationItem() {
        }

        public DefaultViolationItem(String field, String message) {
            this.field = field;
            this.message = message;
        }

        @Override
        public String getField() {
            return field;
        }

        @Override
        public void setField(String field) {
            this.field = field;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            DefaultViolationItem that = (DefaultViolationItem) o;

            return field != null ? field.equals(that.field) : that.field == null;
        }

        @Override
        public int hashCode() {
            return field != null ? field.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "{" + "field='" + field + '\'' + ", message='" + message + '\'' + '}';
        }
    }

    @Override
    public String toString() {
        return "Result{" + "code='" + code + '\'' + ", message='" + message + '\'' + ", data=" + data + ", errorClass='"
            + errorClass + '\'' + ", errorStack='" + errorStack + '\'' + ", violationItems=" + violationItems + '}';
    }
}
