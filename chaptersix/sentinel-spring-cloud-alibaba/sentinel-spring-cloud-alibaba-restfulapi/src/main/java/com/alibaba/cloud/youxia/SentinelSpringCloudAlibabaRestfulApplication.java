package com.alibaba.cloud.youxia;

import com.alibaba.cloud.circuitbreaker.sentinel.SentinelCircuitBreakerFactory;
import com.alibaba.cloud.circuitbreaker.sentinel.SentinelConfigBuilder;
import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;

@SpringBootApplication
public class SentinelSpringCloudAlibabaRestfulApplication {
    @Bean
    @SentinelRestTemplate(blockHandler = "handleException",
            blockHandlerClass = ExceptionUtil.class)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate restTemplate2() {
        return new RestTemplate();
    }

    @Bean
    public Converter myConverter() {
        return new JsonFlowRuleListConverter();
    }

    @Bean
    public Customizer<SentinelCircuitBreakerFactory> defaultConfig() {
        return factory -> {
            factory.configureDefault(
                    id -> new SentinelConfigBuilder().resourceName(id)
                            .rules(Collections.singletonList(new DegradeRule(id)
                                    .setGrade(RuleConstant.DEGRADE_GRADE_RT).setCount(100)
                                    .setTimeWindow(10)))
                            .build());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SentinelSpringCloudAlibabaRestfulApplication.class, args);
    }
}
