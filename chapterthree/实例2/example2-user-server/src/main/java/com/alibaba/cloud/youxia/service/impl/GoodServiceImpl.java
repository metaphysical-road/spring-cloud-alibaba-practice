package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.dto.GoodDTO;
import com.alibaba.cloud.youxia.request.GoodServiceRequest;
import com.alibaba.cloud.youxia.response.DefaultResult;
import com.alibaba.cloud.youxia.service.GoodService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

@DubboService(version = "1.0.0",group = "example2-user-server")
public class GoodServiceImpl implements GoodService {

    private Example2ProductMapper example2ProductMapper;
    @Override
    public DefaultResult<List<GoodDTO>> updateGoodNum(GoodServiceRequest goodServiceRequest) {
        example2ProductMapper.
        return null;
    }
}
