package com.alibaba.cloud.youxia.dynamic.route;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 路由过滤器定义模型
 */
@Data
public class GatewayFilterDefinition {
    /**
     * Filter Name
     */
    private String name;

    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}
