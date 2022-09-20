package com.alibaba.cloud.youxia.executor;

import com.alibaba.cloud.youxia.dto.ProductInfoDTO;
import com.alibaba.cloud.youxia.service.seventeen.ProductInfoService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Date;
@Component
public class DataExecutor {

    @Resource
    private ProductInfoService productInfoService;

    public void insertData(){
        for(int i=0;i<10000;i++){
            ProductInfoDTO productInfoDTO=new ProductInfoDTO();
            productInfoDTO.setProductId(RandomUtils.nextLong());
            productInfoDTO.setBrandId(RandomUtils.nextLong());
            productInfoDTO.setCateId(RandomUtils.nextLong());
            productInfoDTO.setSupplierId(RandomUtils.nextLong());
            productInfoDTO.setCostPrice(1000);
            productInfoDTO.setShopPrice(1200);
            productInfoDTO.setNum(1000);
            productInfoDTO.setStatus(1);
            productInfoDTO.setProductContent("测试商品"+i);
            productInfoDTO.setProductDesc("这个是测试商品");
            productInfoDTO.setProductName("测试商品"+i);
            productInfoDTO.setProductTitle("火鸡"+RandomUtils.nextLong());
            productInfoDTO.setProductShortTitle("火鸡"+i);
            productInfoDTO.setId(RandomUtils.nextLong());
            productInfoDTO.setGmtCreate(new Date());
            productInfoDTO.setGmtModified(new Date());
            productInfoDTO.setIsDeleted(0);
            productInfoService.insert(productInfoDTO);
        }
    }
}
