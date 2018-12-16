package com.springcloud.sellerbuyer.product.service;

import com.springcloud.sellerbuyer.product.dataobject.ProductCategory;

import java.util.List;


public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
