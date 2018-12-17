package com.springcloud.sellerbuyer.product.server.service.impl;

import com.springcloud.sellerbuyer.product.server.dataobject.ProductCategory;
import com.springcloud.sellerbuyer.product.server.repository.ProductCategoryRepository;
import com.springcloud.sellerbuyer.product.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

}
