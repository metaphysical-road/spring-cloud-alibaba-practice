package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.dto.GoodDTO;
import com.alibaba.cloud.youxia.request.GoodServiceRequest;
import com.alibaba.cloud.youxia.response.DefaultResult;
import java.util.List;

public interface GoodService {
    DefaultResult<GoodDTO>  updateGoodNum(GoodServiceRequest goodServiceRequest);
}
