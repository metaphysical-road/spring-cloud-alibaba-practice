package com.alibaba.cloud.youxia.service.seventeen;

import com.alibaba.cloud.youxia.bo.NewProductDetailBo;
import com.alibaba.cloud.youxia.dto.NewProductDetailDTO;

import java.util.List;

public interface NewProductDetailService {
    Integer insert(NewProductDetailDTO newProductDetailDTO);
    List<NewProductDetailDTO> select(NewProductDetailBo newProductDetailBo);
    Integer selectTotalNum(NewProductDetailBo newProductDetailBo);

}
