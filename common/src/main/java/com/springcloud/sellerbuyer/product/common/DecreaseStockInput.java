package com.springcloud.sellerbuyer.product.common;

import lombok.Data;

/**
 * @program: product
 * @description: 减库存对象
 * @author: JunOba
 * @create: 2018-12-17 16:23
 */
@Data
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
