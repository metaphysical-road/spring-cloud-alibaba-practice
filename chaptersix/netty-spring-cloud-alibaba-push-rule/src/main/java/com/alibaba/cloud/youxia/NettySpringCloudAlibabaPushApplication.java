package com.alibaba.cloud.youxia;

import com.alibaba.cloud.youxia.service.RuleService;
import com.alibaba.csp.sentinel.init.InitExecutor;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class NettySpringCloudAlibabaPushApplication {

    public static void main(String[] args) {
        InitExecutor.doInit();
        initFlowRule(5,true);
        SpringApplicationBuilder providerBuilder = new SpringApplicationBuilder();
        providerBuilder.web(WebApplicationType.NONE)
                .sources(NettySpringCloudAlibabaPushApplication.class).run(args);
    }

    private static final String INTERFACE_RES_KEY = RuleService.class.getName();
    private static final String RES_KEY = INTERFACE_RES_KEY + ":rulePush()";

    static void initFlowRule(int interfaceFlowLimit, boolean method) {
        FlowRule flowRule = new FlowRule(INTERFACE_RES_KEY)
                .setCount(interfaceFlowLimit)
                .setGrade(RuleConstant.FLOW_GRADE_QPS);
        List<FlowRule> list = new ArrayList<>();
        if (method) {
            FlowRule flowRule1 = new FlowRule(RES_KEY)
                    .setCount(5)
                    .setGrade(RuleConstant.FLOW_GRADE_QPS);
            list.add(flowRule1);
        }
        list.add(flowRule);
        FlowRuleManager.loadRules(list);
    }
}
