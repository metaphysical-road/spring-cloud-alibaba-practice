package com.alibaba.cloud.youxia.mapper;

import com.alibaba.cloud.youxia.bo.Example5OrderBo;
import com.alibaba.cloud.youxia.entity.Example5OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
public interface Example5OrderMapper extends BaseMapper<Example5OrderEntity> {
    Example5OrderEntity selectOrder(Example5OrderBo example5OrderBo);
}
