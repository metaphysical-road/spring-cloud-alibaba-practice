package com.alibaba.cloud.youxia.service.seventeen;

import com.alibaba.cloud.youxia.bo.ProductInfoBo;
import com.alibaba.cloud.youxia.dto.ProductInfoDTO;
import java.util.List;

public interface ProductInfoService {
    Integer insert(ProductInfoDTO productInfoDTO);
    List<ProductInfoDTO> select(ProductInfoBo productInfoBo);
    Integer selectTotalNum(ProductInfoBo productInfoBo);
}