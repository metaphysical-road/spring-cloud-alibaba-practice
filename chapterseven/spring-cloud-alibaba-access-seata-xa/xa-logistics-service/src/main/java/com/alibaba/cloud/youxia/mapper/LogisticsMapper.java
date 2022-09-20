package com.alibaba.cloud.youxia.mapper;

import com.alibaba.cloud.youxia.entity.LogisticsEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogisticsMapper {
    Integer notifyLogistics(LogisticsEntity logisticsEntity);
}
