package com.alibaba.cloud.youxia.config;

import com.nepxion.discovery.common.constant.DiscoveryConstant;
import com.nepxion.discovery.common.entity.RuleEntity;
import com.nepxion.discovery.plugin.framework.adapter.PluginAdapter;
import com.nepxion.discovery.plugin.framework.cache.RuleCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Configuration
public class ReadConfigService {
    @Autowired
    private PluginAdapter pluginAdapter;
    @PostConstruct
    public void readConfig(){
        ExecutorService executorService= Executors.newFixedThreadPool(1);
        executorService.execute(new ReadConfigThread());
    }

    class ReadConfigThread implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
                RuleEntity ruleEntity=pluginAdapter.getRule();
                if(null!=ruleEntity) {
                    Map<String, Integer> result1 = ruleEntity.getRegisterEntity().getCountFilterEntity().getFilterMap();
                    Integer result2 = ruleEntity.getRegisterEntity().getCountFilterEntity().getFilterValue();
                    System.out.println("基于服务注册数量的规则（指定服务名称）为:" + result1.toString());
                    System.out.println("基于服务注册数量的规则（不指定服务名称）为:" + result2);
                }
            }
        }
    }
}
