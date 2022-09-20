package com.alibaba.cloud.youxia.manager;

import com.alibaba.cloud.youxia.entity.Example2ProductEntity;
import com.alibaba.cloud.youxia.mapper.Example2ProductMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductManager {

    @Resource
    private Example2ProductMapper example2ProductMapper;

    /**
     * 更新库存
     */
    public void updateByEntitySucc() {
        QueryWrapper<Example2ProductEntity> ew = new QueryWrapper<>();
        ew.eq("id",3477374334L);
        Long id;
        //查询商品信息
        Example2ProductEntity goodA=example2ProductMapper.selectOne(ew);
        Example2ProductEntity goodB=example2ProductMapper.selectOne(ew);
        id=goodB.getId();
        //商品A加库存50
        goodA.setNum(goodA.getNum()+50);
        //执行数据库的更新操作
        example2ProductMapper.updateById(goodA);
        //商品B减库存30
        goodB.setNum(goodB.getNum()-30);
        //执行数据库的更新操作
        int result=example2ProductMapper.updateById(goodB);

        if(result==0){
            //扣减失败，重新扣减
            Example2ProductEntity goodC=example2ProductMapper.selectById(goodB.getId());
            goodC.setNum(goodC.getNum()-30);
            example2ProductMapper.updateById(goodC);
        }
        Example2ProductEntity newEntity=example2ProductMapper.selectById(id);
        System.out.println("当前库存为："+newEntity.getNum());
    }
}
