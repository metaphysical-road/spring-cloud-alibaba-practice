package com.alibaba.cloud.youxia.manager;

import com.alibaba.cloud.youxia.bo.AnchorBo;
import com.alibaba.cloud.youxia.entity.AnchorEntity;
import com.alibaba.cloud.youxia.mapper.AnchorMapper;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnchorManager {
    @Autowired
    private AnchorMapper anchorMapper;

    public Integer insert(AnchorBo anchorBo){
        AnchorEntity anchorEntity=new AnchorEntity();
        anchorEntity.setAnchorName(anchorBo.getAnchorName());
        anchorEntity.setAnchorLevel(anchorBo.getAnchorLevel());
        Integer integer=anchorMapper.insert(anchorEntity);
        return integer;
    }
}
