package com.alibaba.cloud.youxia.dynamic.route;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 路由过滤器定义模型
 */
//@Data
public class GatewayFilterDefinition {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }

    /**
     * Filter Name
     */
    private String name;

    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}
