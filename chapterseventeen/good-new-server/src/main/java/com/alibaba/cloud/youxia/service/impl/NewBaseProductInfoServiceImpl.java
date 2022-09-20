package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.bo.NewBaseProductInfoBo;
import com.alibaba.cloud.youxia.dto.NewBaseProductInfoDTO;
import com.alibaba.cloud.youxia.dto.ProductInfoDTO;
import com.alibaba.cloud.youxia.entity.BaseProductInfoEntity;
import com.alibaba.cloud.youxia.mapper.BaseProductInfoMapper;
import com.alibaba.cloud.youxia.service.seventeen.NewBaseProductInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@DubboService(version = "1.0.0",group = "data-sync",timeout = 20000)
public class NewBaseProductInfoServiceImpl implements NewBaseProductInfoService {
    @Resource
    private BaseProductInfoMapper baseProductInfoMapper;

    @Override
    public Integer insert(NewBaseProductInfoDTO newBaseProductInfoDTO) {
        BaseProductInfoEntity baseProductInfoEntity=new BaseProductInfoEntity();
        baseProductInfoEntity.setProductId(newBaseProductInfoDTO.getProductId());
        baseProductInfoEntity.setProductName(newBaseProductInfoDTO.getProductName());
        baseProductInfoEntity.setBrandId(newBaseProductInfoDTO.getBrandId());
        baseProductInfoEntity.setCostPrice(newBaseProductInfoDTO.getCostPrice());
        baseProductInfoEntity.setShopPrice(newBaseProductInfoDTO.getShopPrice());
        baseProductInfoEntity.setGmtCreate(newBaseProductInfoDTO.getGmtCreate());
        baseProductInfoEntity.setGmtModified(newBaseProductInfoDTO.getGmtModified());
        baseProductInfoEntity.setCateId(newBaseProductInfoDTO.getCateId());
        baseProductInfoEntity.setSupplierId(newBaseProductInfoDTO.getSupplierId());
        baseProductInfoEntity.setStatus(newBaseProductInfoDTO.getNum());
        baseProductInfoEntity.setIsDeleted(newBaseProductInfoDTO.getIsDeleted());
        return baseProductInfoMapper.insert(baseProductInfoEntity);
    }

    @Override
    public Integer selectTotalNum(NewBaseProductInfoBo newBaseProductInfoBo) {
        QueryWrapper<BaseProductInfoEntity> queryWrapper = new QueryWrapper();
        queryWrapper.gt("product_id",0);
        return baseProductInfoMapper.selectCount(queryWrapper);
    }

    @Override
    public List<NewBaseProductInfoDTO> select(NewBaseProductInfoBo newBaseProductInfoBo) {
        QueryWrapper<BaseProductInfoEntity> queryWrapper = new QueryWrapper();
        if(StringUtils.isNotEmpty(newBaseProductInfoBo.getProductId()+"")){
            queryWrapper.eq("product_id", newBaseProductInfoBo.getProductId());
        }
        List<BaseProductInfoEntity> queryResult = baseProductInfoMapper.selectList(queryWrapper);
        List<NewBaseProductInfoDTO> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(queryResult)) {
            for (BaseProductInfoEntity item : queryResult) {
                NewBaseProductInfoDTO newBaseProductInfoDTO = new NewBaseProductInfoDTO();
                newBaseProductInfoDTO.setProductId(item.getProductId());
                newBaseProductInfoDTO.setProductName(item.getProductName());
                newBaseProductInfoDTO.setBrandId(item.getBrandId());
                newBaseProductInfoDTO.setCostPrice(item.getCostPrice());
                newBaseProductInfoDTO.setShopPrice(item.getShopPrice());
                newBaseProductInfoDTO.setGmtCreate(item.getGmtCreate());
                newBaseProductInfoDTO.setGmtModified(item.getGmtModified());
                newBaseProductInfoDTO.setIsDeleted(item.getIsDeleted());
                result.add(newBaseProductInfoDTO);
            }
        }
        return result;
    }
}
