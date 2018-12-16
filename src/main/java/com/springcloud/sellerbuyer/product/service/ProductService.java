package com.springcloud.sellerbuyer.product.service;

import com.springcloud.sellerbuyer.product.dataobject.ProductInfo;


import java.util.List;

public interface ProductService {

    List<ProductInfo> findUpAll();

}
