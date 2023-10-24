package com.alibaba.cloud.youxia.dynamic.route;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 创建路由断言定义模型
 */
//@Data
public class GatewayPredicateDefinition {
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
     * 断言对应的Name
     */
    private String name;

    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}
