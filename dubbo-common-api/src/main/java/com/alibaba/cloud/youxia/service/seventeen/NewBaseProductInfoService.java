package com.alibaba.cloud.youxia.service.seventeen;

import com.alibaba.cloud.youxia.bo.NewBaseProductInfoBo;
import com.alibaba.cloud.youxia.dto.NewBaseProductInfoDTO;

import java.util.List;

public interface NewBaseProductInfoService {
    Integer insert(NewBaseProductInfoDTO newBaseProductInfoDTO);
    List<NewBaseProductInfoDTO> select(NewBaseProductInfoBo newBaseProductInfoBo);
    Integer selectTotalNum(NewBaseProductInfoBo newBaseProductInfoBo);
}
