package com.springcloud.sellerbuyer.product.server.repository;

import com.springcloud.sellerbuyer.product.server.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
    * @Description: 根据商品类型查询商品
    * @Param: 数据库中的category_type字段集合
    * @return: 满足查询的数据库对象列表
    * @Author: JunOba
    * @Date: 2018/12/16
    */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
