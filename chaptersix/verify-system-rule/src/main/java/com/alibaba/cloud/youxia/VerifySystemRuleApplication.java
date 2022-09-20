package com.alibaba.cloud.youxia;
import com.alibaba.cloud.youxia.service.SystemRuleService;
import com.alibaba.csp.sentinel.init.InitExecutor;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
public class VerifySystemRuleApplication {
    public static void main(String[] args) {
        InitExecutor.doInit();
        initDegradeRule(10,true);
        SpringApplication.run(VerifySystemRuleApplication.class, args);
    }

    private static final String INTERFACE_RES_KEY = SystemRuleService.class.getName();
    private static final String RES_KEY = INTERFACE_RES_KEY + ":verifySystemRule()";

    static void initDegradeRule(int interfaceDegradeLimit, boolean method) {
        SystemRule systemRule = new SystemRule();
        systemRule.setResource(INTERFACE_RES_KEY);
        systemRule.setAvgRt(2000);
        systemRule.setMaxThread(5000);
        systemRule.setQps(300000);
        systemRule.setHighestSystemLoad(1.5);
        systemRule.setHighestCpuUsage(0.8);
        List<SystemRule> list = new ArrayList<>();
        if (method) {
            SystemRule systemRule1 = new SystemRule();
            systemRule1.setResource(RES_KEY);
            systemRule1.setAvgRt(2000);
            systemRule1.setQps(300000);
            systemRule1.setHighestSystemLoad(1.5);
            systemRule1.setMaxThread(5000);
            systemRule1.setHighestCpuUsage(0.8);
            list.add(systemRule1);
        }
        list.add(systemRule);
        SystemRuleManager.loadRules(list);
    }
}
