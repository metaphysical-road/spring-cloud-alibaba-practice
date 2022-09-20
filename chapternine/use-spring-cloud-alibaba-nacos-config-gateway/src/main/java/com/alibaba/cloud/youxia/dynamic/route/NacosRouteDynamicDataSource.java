package com.alibaba.cloud.youxia.dynamic.route;

import com.alibaba.cloud.nacos.NacosConfigManager;
//import com.alibaba.cloud.youxia.config.GatewayConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RefreshScope
@Slf4j
public class NacosRouteDynamicDataSource implements ApplicationRunner {
    @Autowired
    private NacosConfigManager nacosConfigManager;

    private ConfigService configService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ExecutorService executorService= Executors.newFixedThreadPool(1);
        executorService.execute(new RefreshRouteCache());
    }

    class RefreshRouteCache implements Runnable{
        @Override
        public void run() {
            while (true) {
                log.info("gateway route init...");
                try {
                    if (null == configService) {
                        configService = nacosConfigManager.getConfigService();
                    }
                    String configInfo = configService.getConfig("gateway-dynamic-route-rule.json", "gateway-dynamic-route-rule"
                            , 4000);
                    log.info("获取网关当前配置:\r\n{}", configInfo);
                    List<RouteDefinition> definitionList = JSON.parseArray(configInfo, RouteDefinition.class);
                    for (RouteDefinition definition : definitionList) {
                        log.info("update route : {}", definition.toString());
                        dynamicRouteService.add(definition);
                    }
                    Thread.sleep(20000);
                } catch (Exception e) {
                    log.error("初始化网关路由时发生错误", e);
                }
                dynamicRouteByNacosListener("gateway-dynamic-route-rule.json", "gateway-dynamic-route-rule");
            }
        }
    }

    public void dynamicRouteByNacosListener (String dataId, String group){
        try {
            configService.addListener(dataId, group, new Listener()  {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("进行网关更新:\n\r{}",configInfo);
                    List<RouteDefinition> definitionList = JSON.parseArray(configInfo, RouteDefinition.class);
                    log.info("update route : {}",definitionList.toString());
                    dynamicRouteService.updateList(definitionList);
                }
                @Override
                public Executor getExecutor() {
                    log.info("getExecutor\n\r");
                    return null;
                }
            });
        } catch (NacosException e) {
            log.error("从nacos接收动态路由配置出错!!!",e);
        }
    }

    @Resource
    private DynamicRouteService dynamicRouteService;
}
