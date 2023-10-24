package com.alibaba.cloud.youxia.dynamic.route;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建路由定义模型
 */
//@Data
public class GatewayRouteDefinition {
    /**
     * 路由的Id
     */
    private String id;

    /**
     * 路由断言集合配置
     */
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GatewayPredicateDefinition> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<GatewayPredicateDefinition> predicates) {
        this.predicates = predicates;
    }

    public List<GatewayFilterDefinition> getFilters() {
        return filters;
    }

    public void setFilters(List<GatewayFilterDefinition> filters) {
        this.filters = filters;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * 路由过滤器集合配置
     */
    private List<GatewayFilterDefinition> filters = new ArrayList<>();

    /**
     * 路由规则转发的目标uri
     */
    private String uri;

    /**
     * 路由执行的顺序
     */
    private int order = 0;
}
