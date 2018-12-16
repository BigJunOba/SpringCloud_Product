package com.springcloud.sellerbuyer.product.service.impl;

import com.springcloud.sellerbuyer.product.dataobject.ProductInfo;
import com.springcloud.sellerbuyer.product.enums.ProductStatusEnum;
import com.springcloud.sellerbuyer.product.repository.ProductInfoRepository;
import com.springcloud.sellerbuyer.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

}
