package com.alibaba.cloud.youxia;

import com.alibaba.cloud.youxia.service.DegradeService;
import com.alibaba.cloud.youxia.service.VerifyFlowService;
import com.alibaba.csp.sentinel.init.InitExecutor;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class VerifyDegradeApplication {
    public static void main(String[] args) {
        InitExecutor.doInit();
        initDegradeRule(10,true);
        SpringApplication.run(VerifyDegradeApplication.class, args);
    }

    private static final String INTERFACE_RES_KEY = DegradeService.class.getName();
    private static final String RES_KEY = INTERFACE_RES_KEY + ":verifyDegradeRule()";

    static void initDegradeRule(int interfaceDegradeLimit, boolean method) {
        DegradeRule degradeRule = new DegradeRule(INTERFACE_RES_KEY)
                .setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT).setMinRequestAmount(interfaceDegradeLimit).setCount(8).setTimeWindow(5);
        List<DegradeRule> list = new ArrayList<>();
        if (method) {
            DegradeRule degradeRule1 = new DegradeRule(RES_KEY)
                    .setCount(8).setMinRequestAmount(interfaceDegradeLimit)
                    .setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT).setTimeWindow(5);
            list.add(degradeRule1);
        }
        list.add(degradeRule);
        DegradeRuleManager.loadRules(list);
    }
}
