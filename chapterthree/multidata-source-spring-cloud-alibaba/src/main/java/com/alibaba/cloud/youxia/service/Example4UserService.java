package com.alibaba.cloud.youxia.service;

import com.alibaba.cloud.youxia.bo.Example2ProductBo;
import com.alibaba.cloud.youxia.entity.Example2ProductEntity;

import java.util.List;

public interface Example4UserService {

    List<Example2ProductEntity> selectFromMaster(Example2ProductBo example2ProductBo);

    List<Example2ProductEntity> selectFromSlave1(Example2ProductBo example2ProductBo);

    List<Example2ProductEntity> selectFromSlave2(Example2ProductBo example2ProductBo);
}
