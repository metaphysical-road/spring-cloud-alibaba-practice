package com.alibaba.cloud.youxia.config;

import com.alibaba.cloud.youxia.service.RuleService;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FlowRuleConfig {

//    private static final String INTERFACE_RES_KEY = RuleService.class.getName();
//    private static final String RES_KEY = INTERFACE_RES_KEY + ":rulePush()";
//
//    public void initFlowRule(int interfaceFlowLimit, boolean method) {
//        FlowRule flowRule = new FlowRule(INTERFACE_RES_KEY)
//                .setCount(interfaceFlowLimit)
//                .setGrade(RuleConstant.FLOW_GRADE_QPS);
//        List<FlowRule> list = new ArrayList<>();
//        if (method) {
//            FlowRule flowRule1 = new FlowRule(RES_KEY)
//                    .setCount(5)
//                    .setGrade(RuleConstant.FLOW_GRADE_QPS);
//            list.add(flowRule1);
//        }
//        list.add(flowRule);
//        FlowRuleManager.loadRules(list);
//    }
}
