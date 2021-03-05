package com.alibaba.cloud.youxia.consumer;

import com.alibaba.cloud.youxia.service.OrderServiceProvider;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component("orderServiceConsumer")
public class OrderServiceConsumer {
    @DubboReference(group = "example8", version = "1.0.0")
    private OrderServiceProvider orderServiceProvider;

    public String getOrderName() {
        return orderServiceProvider.getOrderName();
    }

    @Override
    public void doRegister(GetURL url) {
        try {
            //针对服务提供者的URL，在Zookeeper上注册临时节点
            zkClient.create(toUrlPath(url), url.getParameter(DYNAMIC_KEY, true));
        } catch (Throwable e) {
            throw new RpcException("Failed to register " + url + " to zookeeper " + getUrl() + ", cause: " + e.getMessage(), e);
        }
    }

}
