package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.bo.AnchorBo;
import com.alibaba.cloud.youxia.manager.AnchorManager;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(version = "1.0.0",group = "use-es-skywalking-provider")
public class AnchorServiceImpl implements AnchorService{

    @Resource
    private AnchorManager anchorManager;

    @Override
    public Integer insertAnchor(AnchorBo anchorBo) {
        return anchorManager.insert(anchorBo);
    }
}
