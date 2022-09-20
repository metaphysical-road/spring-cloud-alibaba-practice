package com.alibaba.cloud.youxia.controller;
import com.alibaba.cloud.youxia.bo.NewBaseProductInfoBo;
import com.alibaba.cloud.youxia.bo.NewProductDetailBo;
import com.alibaba.cloud.youxia.bo.ProductInfoBo;
import com.alibaba.cloud.youxia.dto.NewBaseProductInfoDTO;
import com.alibaba.cloud.youxia.dto.NewProductDetailDTO;
import com.alibaba.cloud.youxia.dto.ProductInfoDTO;
import com.alibaba.cloud.youxia.service.seventeen.NewBaseProductInfoService;
import com.alibaba.cloud.youxia.service.seventeen.NewProductDetailService;
import com.alibaba.cloud.youxia.service.seventeen.ProductInfoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value = "/good")
public class GoodController {
    @DubboReference(version = "1.0.0",group = "data-sync",timeout = 20000)
    private NewBaseProductInfoService newBaseProductInfoService;

    @DubboReference(version = "1.0.0",group = "data-sync",timeout = 20000)
    private NewProductDetailService newProductDetailService;

    @DubboReference(version = "1.0.0",group = "data-sync",timeout = 20000)
    private ProductInfoService productInfoService;

    @GetMapping(value = "/getold")
    public String getGoodInfoFromOldDataBase(){
        ProductInfoBo productInfoBo=new ProductInfoBo();
        return "旧商品数据库中的商品数为："+productInfoService.selectTotalNum(productInfoBo);
    }

    @GetMapping(value = "/getnew")
    public String getGoodInfoFromNewDataBase(){
        NewBaseProductInfoBo newBaseProductInfoBo=new NewBaseProductInfoBo();
        Integer resultBase=newBaseProductInfoService.selectTotalNum(newBaseProductInfoBo);
        NewProductDetailBo newProductDetailBo=new NewProductDetailBo();
        Integer resultDetail=newProductDetailService.selectTotalNum(newProductDetailBo);
        return "新商品数据库中的基础商品数为："+resultBase+";商品详情数为："+resultDetail;
    }

    @GetMapping(value = "/compare")
    public String compare(Long productId) {
//        ExecutorService executorService= Executors.newCachedThreadPool();
//        executorService.execute(new CompareThread(productId));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("开始对比商品数据");
        List<ProductInfoDTO> result = new ArrayList<>();
        ProductInfoBo productInfoBo = new ProductInfoBo();
        productInfoBo.setProductId(productId);
        result = productInfoService.select(productInfoBo);
        NewBaseProductInfoBo newBaseProductInfoBo = new NewBaseProductInfoBo();
        newBaseProductInfoBo.setProductId(productId);
        List<NewBaseProductInfoDTO> newBaseProductInfoDTOList = newBaseProductInfoService.select(newBaseProductInfoBo);
        Map<Long, NewBaseProductInfoDTO> newBaseProductInfoDTOMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(newBaseProductInfoDTOList)) {
            for (NewBaseProductInfoDTO newBaseProductInfoDTO : newBaseProductInfoDTOList) {
                newBaseProductInfoDTOMap.put(newBaseProductInfoDTO.getProductId(), newBaseProductInfoDTO);
            }
        }
        NewProductDetailBo newProductDetailBo = new NewProductDetailBo();
        newProductDetailBo.setProductId(productId);
        List<NewProductDetailDTO> newProductDetailDTOList = newProductDetailService.select(newProductDetailBo);
        Map<Long, NewProductDetailDTO> newProductDetailDTOMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(newProductDetailDTOList)) {
            for (NewProductDetailDTO newProductDetailDTO : newProductDetailDTOList) {
                newProductDetailDTOMap.put(newProductDetailDTO.getProductId(), newProductDetailDTO);
            }
        }
        if (!CollectionUtils.isEmpty(result)) {
            for (ProductInfoDTO productInfoDTO : result) {
                boolean isSame = true;
                NewProductDetailDTO matchNewProductDetailDTO = newProductDetailDTOMap.get(productInfoDTO.getProductId());
                NewBaseProductInfoDTO matchNewBaseProductInfoDTO = newBaseProductInfoDTOMap.get(productInfoDTO.getProductId());
                if (!productInfoDTO.getProductShortTitle().equals(matchNewProductDetailDTO.getProductShortTitle())) {
                    isSame = false;
                }
                if (productInfoDTO.getShopPrice().intValue() != matchNewBaseProductInfoDTO.getShopPrice().intValue()) {
                    isSame = false;
                }
                if (isSame) {
                    stringBuilder.append("新旧商品库中商品信息相同：").append(productInfoDTO.toString());
                    System.out.println("新旧商品库中商品信息相同：" + productInfoDTO.toString());
                }
            }
        }
        return stringBuilder.toString();
    }

    class CompareThread implements Runnable{
        private Long productId;
        public CompareThread(Long productId){
            this.productId=productId;
        }
        @Override
        public void run() {
            while (true) {
                List<ProductInfoDTO> result = new ArrayList<>();
                ProductInfoBo productInfoBo = new ProductInfoBo();
                productInfoBo.setProductId(productId);
                result = productInfoService.select(productInfoBo);
                NewBaseProductInfoBo newBaseProductInfoBo = new NewBaseProductInfoBo();
                List<NewBaseProductInfoDTO> newBaseProductInfoDTOList = newBaseProductInfoService.select(newBaseProductInfoBo);
                Map<Long, NewBaseProductInfoDTO> newBaseProductInfoDTOMap = new HashMap<>();
                if (!CollectionUtils.isEmpty(newBaseProductInfoDTOList)) {
                    for (NewBaseProductInfoDTO newBaseProductInfoDTO : newBaseProductInfoDTOList) {
                        newBaseProductInfoDTOMap.put(newBaseProductInfoDTO.getProductId(), newBaseProductInfoDTO);
                    }
                }
                NewProductDetailBo newProductDetailBo = new NewProductDetailBo();
                newProductDetailBo.setProductId(productId);
                List<NewProductDetailDTO> newProductDetailDTOList = newProductDetailService.select(newProductDetailBo);
                Map<Long, NewProductDetailDTO> newProductDetailDTOMap = new HashMap<>();
                if (!CollectionUtils.isEmpty(newProductDetailDTOList)) {
                    for (NewProductDetailDTO newProductDetailDTO : newProductDetailDTOList) {
                        newProductDetailDTOMap.put(newProductDetailDTO.getProductId(), newProductDetailDTO);
                    }
                }
                if (!CollectionUtils.isEmpty(result)) {
                    for (ProductInfoDTO productInfoDTO : result) {
                        boolean isSame = true;
                        NewProductDetailDTO matchNewProductDetailDTO = newProductDetailDTOMap.get(productInfoDTO.getProductId());
                        NewBaseProductInfoDTO matchNewBaseProductInfoDTO = newBaseProductInfoDTOMap.get(productInfoDTO.getProductId());
                        if (!productInfoDTO.getProductShortTitle().equals(matchNewProductDetailDTO.getProductShortTitle())) {
                            isSame = false;
                        }
                        if (productInfoDTO.getShopPrice().intValue() == matchNewBaseProductInfoDTO.getShopPrice().intValue()) {
                            isSame = false;
                        }
                        if (isSame) {
                            System.out.println("新旧商品库中商品信息相同：" + productInfoDTO.toString());
                        }
                    }
                }
            }
        }
    }
}
