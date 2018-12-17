package com.springcloud.sellerbuyer.product.server.service;

import com.springcloud.sellerbuyer.product.common.DecreaseStockInput;
import com.springcloud.sellerbuyer.product.common.ProductInfoOutput;
import com.springcloud.sellerbuyer.product.server.dataobject.ProductInfo;


import java.util.List;

public interface ProductService {

    /**
     * 查询所有再加商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     * @param decreaseStockInputList 购物车列表
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
