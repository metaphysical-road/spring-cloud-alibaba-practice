package com.alibaba.cloud.youxia.gateway;

import com.nepxion.discovery.plugin.framework.adapter.PluginAdapter;
import com.nepxion.discovery.plugin.strategy.adapter.DiscoveryEnabledStrategy;
import com.nepxion.discovery.plugin.strategy.gateway.context.GatewayStrategyContextHolder;
import com.netflix.loadbalancer.Server;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GatewayDiscoveryEnabledStrategy implements DiscoveryEnabledStrategy {
    private static final Logger LOG = LoggerFactory.getLogger(GatewayDiscoveryEnabledStrategy.class);

    @Autowired
    private GatewayStrategyContextHolder gatewayStrategyContextHolder;

    @Autowired
    private PluginAdapter pluginAdapter;

    @Override
    public boolean apply(Server server) {
        return applyFromHeader(server);
    }
    private boolean applyFromHeader(Server server) {
        String goodId = gatewayStrategyContextHolder.getHeader("goodId");
        String serviceId = pluginAdapter.getServerServiceId(server);
        String version = pluginAdapter.getServerVersion(server);
        String region = pluginAdapter.getServerRegion(server);
        String environment = pluginAdapter.getServerEnvironment(server);
        String address = server.getHost() + ":" + server.getPort();
        LOG.info("负载均衡用户定制触发：goodId={}, serviceId={}, version={}, region={}, env={}, address={}", goodId, serviceId, version, region, environment, address);
        if (StringUtils.isNotEmpty(goodId)) {
            // 手机号以移动138开头，路由到1.0版本的服务上
            if (goodId.startsWith("347737") && StringUtils.equals(version, "1.0")) {
                return true;
                // 手机号以联通133开头，路由到2.0版本的服务上
            } else if (goodId.startsWith("346737") && StringUtils.equals(version, "1.1")) {
                return true;
            } else {
                // 其它情况，直接拒绝请求
                return false;
            }
        }
        return true;
    }
}
