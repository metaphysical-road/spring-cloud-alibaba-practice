package com.alibaba.cloud.youxia.service.impl;

import com.alibaba.cloud.youxia.bo.Example2ProductBo;
import com.alibaba.cloud.youxia.dto.GoodDTO;
import com.alibaba.cloud.youxia.entity.Example2ProductEntity;
import com.alibaba.cloud.youxia.mapper.Example2ProductMapper;
import com.alibaba.cloud.youxia.request.GoodServiceRequest;
import com.alibaba.cloud.youxia.response.DefaultResult;
import com.alibaba.cloud.youxia.service.GoodService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.concurrent.TimeUnit;

@DubboService(version = "1.0.0",group = "idempotent-design-user-server")
public class GoodServiceImpl implements GoodService {

    @Autowired
    private Example2ProductMapper example2ProductMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public DefaultResult<GoodDTO> updateGoodNum(GoodServiceRequest goodServiceRequest) {
        DefaultResult<GoodDTO> result=new DefaultResult<>();
        GoodDTO returnItem=new GoodDTO();
        Example2ProductBo example2ProductBo=(Example2ProductBo)goodServiceRequest.getRequestData();
        //开启幂等性设计
        if(!StringUtils.isEmpty(goodServiceRequest.getUuid())){
            long uuid=Long.parseLong(goodServiceRequest.getUuid());
            if(null!=redisTemplate.opsForValue().get(uuid)){
                result.setData(new GoodDTO());
                result.setMessage("uuid:"+uuid+" 已经连续访问多次！");
                return result;
            }else{
                redisTemplate.opsForValue().set(uuid,true,5, TimeUnit.SECONDS);
            }
        }
        try {
            //设置执行延时时间2s
            Thread.sleep(2000);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        List<Example2ProductEntity> queryResult1= example2ProductMapper.queryGoodInfoByGoodId(example2ProductBo);
        if(!CollectionUtils.isEmpty(queryResult1)){
            Example2ProductEntity item=queryResult1.get(0);
            System.out.println("开始扣减库存，扣除之前的商品库存为："+item.getNum()+" 商品ID为："+item.getGoodId());
        }
        example2ProductMapper.updateGoodNum(example2ProductBo);
        List<Example2ProductEntity> queryResult2= example2ProductMapper.queryGoodInfoByGoodId(example2ProductBo);
        if(!CollectionUtils.isEmpty(queryResult2)){
            Example2ProductEntity item=queryResult2.get(0);
            System.out.println("开始扣减库存，扣除之后的商品库存为："+item.getNum()+" 商品ID为："+item.getGoodId());
        }
        returnItem.setGoodId(queryResult2.get(0).getGoodId());
        returnItem.setNum(queryResult2.get(0).getNum());
        result.setData(returnItem);
        result.setCode("200");
        result.setMessage("库存扣减成功！！！！！");
        return result;
    }
}
