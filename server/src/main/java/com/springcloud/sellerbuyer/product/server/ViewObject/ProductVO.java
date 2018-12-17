package com.springcloud.sellerbuyer.product.server.ViewObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO{

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    // 只是ProductInfo对象的部分字段，例如库存，上下架状态之类的不返回
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
