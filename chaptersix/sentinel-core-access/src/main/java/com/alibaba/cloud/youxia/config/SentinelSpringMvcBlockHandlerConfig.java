package com.alibaba.cloud.youxia.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order(0)
public class SentinelSpringMvcBlockHandlerConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BlockException.class)
    @ResponseBody
    public ResultWrapper sentinelBlockHandler(BlockException e) {
        logger.warn("Blocked by Sentinel: {}", e.getRule());
        return ResultWrapper.blocked();
    }
}
