package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.bo.ProductInfoBo;
import com.alibaba.cloud.youxia.dto.ProductInfoDTO;
import com.alibaba.cloud.youxia.entity.ProductInfoEntity;
import com.alibaba.cloud.youxia.mapper.ProductInfoMapper;
import com.alibaba.cloud.youxia.service.seventeen.ProductInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@DubboService(version = "1.0.0",group = "data-sync",timeout = 20000)
public class ProductInfoServiceImpl implements ProductInfoService {

    @Resource
    private ProductInfoMapper productInfoMapper;

    @Override
    public Integer insert(ProductInfoDTO productInfoDTO) {
        ProductInfoEntity productInfoEntity=new ProductInfoEntity();
        productInfoEntity.setProductId(productInfoDTO.getProductId());
        productInfoEntity.setProductDesc(productInfoDTO.getProductDesc());
        productInfoEntity.setProductName(productInfoDTO.getProductName());
        productInfoEntity.setProductTitle(productInfoDTO.getProductTitle());
        productInfoEntity.setProductShortTitle(productInfoDTO.getProductShortTitle());
        productInfoEntity.setProductContent(productInfoDTO.getProductContent());
        productInfoEntity.setBrandId(productInfoDTO.getBrandId());
        productInfoEntity.setSupplierId(productInfoDTO.getSupplierId());
        productInfoEntity.setCateId(productInfoDTO.getCateId());
        productInfoEntity.setNum(productInfoDTO.getNum());
        productInfoEntity.setCostPrice(productInfoDTO.getCostPrice());
        productInfoEntity.setShopPrice(productInfoDTO.getShopPrice());
        productInfoEntity.setStatus(productInfoDTO.getStatus());
        productInfoEntity.setIsDeleted(productInfoDTO.getIsDeleted());
        productInfoEntity.setGmtCreate(productInfoDTO.getGmtCreate());
        productInfoEntity.setGmtModified(productInfoDTO.getGmtModified());
        return productInfoMapper.insert(productInfoEntity);
    }

    @Override
    public Integer selectTotalNum(ProductInfoBo productInfoBo) {
        QueryWrapper<ProductInfoEntity> queryWrapper = new QueryWrapper();
        queryWrapper.gt("product_id",0);
        return productInfoMapper.selectCount(queryWrapper);
    }

    @Override
    public List<ProductInfoDTO> select(ProductInfoBo productInfoBo) {
        QueryWrapper<ProductInfoEntity> queryWrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(productInfoBo.getProductId()+"")){
            queryWrapper.eq("product_id", productInfoBo.getProductId());
        }
        List<ProductInfoEntity> queryResult = productInfoMapper.selectList(queryWrapper);
        List<ProductInfoDTO> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(queryResult)) {
            for (ProductInfoEntity item : queryResult) {
                ProductInfoDTO productInfoDTO = new ProductInfoDTO();
                productInfoDTO.setProductName(item.getProductName());
                productInfoDTO.setVersion(item.getVersion());
                productInfoDTO.setProductDesc(item.getProductDesc());
                productInfoDTO.setProductTitle(item.getProductTitle());
                productInfoDTO.setProductContent(item.getProductContent());
                productInfoDTO.setProductId(item.getProductId());
                productInfoDTO.setProductShortTitle(item.getProductShortTitle());
                productInfoDTO.setStatus(item.getStatus());
                productInfoDTO.setNum(item.getNum());
                productInfoDTO.setIsDeleted(item.getIsDeleted());
                productInfoDTO.setGmtModified(item.getGmtModified());
                productInfoDTO.setGmtCreate(item.getGmtCreate());
                productInfoDTO.setShopPrice(item.getShopPrice());
                productInfoDTO.setCostPrice(item.getCostPrice());
                result.add(productInfoDTO);
            }
        }
        return result;
    }
}
