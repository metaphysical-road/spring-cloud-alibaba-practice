package com.alibaba.cloud.youxia.service.fiveteen;

import com.alibaba.cloud.youxia.bo.FiveteenTradeBo;
import com.alibaba.cloud.youxia.dto.FiveteenTradeDTO;

public interface FiveteenTradeService {
    FiveteenTradeDTO buyGoodAndCreateOrder(FiveteenTradeBo fiveteenTradeBo);
}
