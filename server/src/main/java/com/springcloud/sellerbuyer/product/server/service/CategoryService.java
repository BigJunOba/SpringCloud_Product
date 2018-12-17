package com.springcloud.sellerbuyer.product.server.service;

import com.springcloud.sellerbuyer.product.server.dataobject.ProductCategory;

import java.util.List;


public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
