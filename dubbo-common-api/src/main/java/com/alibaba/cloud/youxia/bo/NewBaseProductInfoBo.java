package com.alibaba.cloud.youxia.bo;

import java.io.Serializable;

public class NewBaseProductInfoBo implements Serializable {
    private Long productId;

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
