package com.alibaba.cloud.youxia.service.fiveteen;

import com.alibaba.cloud.youxia.bo.FiveteenOrderBo;
import com.alibaba.cloud.youxia.dto.FiveteenOrderDTO;

public interface FiveteenOrderService {
    FiveteenOrderDTO createOrder(FiveteenOrderBo fiveteenOrderBo);
}
