package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.bo.NewProductDetailBo;
import com.alibaba.cloud.youxia.dto.NewProductDetailDTO;
import com.alibaba.cloud.youxia.entity.ProductDetailEntity;
import com.alibaba.cloud.youxia.mapper.ProductDetailMapper;
import com.alibaba.cloud.youxia.service.seventeen.NewProductDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@DubboService(version = "1.0.0",group = "data-sync",timeout = 20000)
public class NewProductDetailServiceImpl implements NewProductDetailService {
    @Resource
    private ProductDetailMapper productDetailMapper;
    @Override
    public Integer insert(NewProductDetailDTO newProductDetailDTO) {
        ProductDetailEntity productDetailEntity=new ProductDetailEntity();
        productDetailEntity.setProductId(newProductDetailDTO.getProductId());
        productDetailEntity.setProductDesc(newProductDetailDTO.getProductDesc());
        productDetailEntity.setProductTitle(newProductDetailDTO.getProductTitle());
        productDetailEntity.setProductContent(newProductDetailDTO.getProductContent());
        productDetailEntity.setGmtCreate(newProductDetailDTO.getGmtCreate());
        productDetailEntity.setIsDeleted(newProductDetailDTO.getIsDeleted());
        productDetailEntity.setGmtModified(newProductDetailDTO.getGmtModified());
        return productDetailMapper.insert(productDetailEntity);
    }

    @Override
    public Integer selectTotalNum(NewProductDetailBo newProductDetailBo) {
        QueryWrapper<ProductDetailEntity> queryWrapper = new QueryWrapper();
        queryWrapper.gt("product_id",0);
        return productDetailMapper.selectCount(queryWrapper);
    }

    @Override
    public List<NewProductDetailDTO> select(NewProductDetailBo newProductDetailBo) {
        QueryWrapper<ProductDetailEntity> queryWrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(newProductDetailBo.getProductId()+"")){
            queryWrapper.eq("product_id", newProductDetailBo.getProductId());
        }
        List<ProductDetailEntity> queryResult = productDetailMapper.selectList(queryWrapper);
        List<NewProductDetailDTO> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(queryResult)) {
            for (ProductDetailEntity item : queryResult) {
                NewProductDetailDTO newProductDetailDTO = new NewProductDetailDTO();
                newProductDetailDTO.setProductId(item.getProductId());
                newProductDetailDTO.setProductDesc(item.getProductDesc());
                newProductDetailDTO.setProductContent(item.getProductContent());
                newProductDetailDTO.setProductShortTitle(item.getProductShortTitle());
                newProductDetailDTO.setProductTitle(item.getProductTitle());
                newProductDetailDTO.setGmtCreate(item.getGmtCreate());
                newProductDetailDTO.setGmtModified(item.getGmtModified());
                newProductDetailDTO.setIsDeleted(item.getIsDeleted());
                result.add(newProductDetailDTO);
            }
        }
        return result;
    }
}
