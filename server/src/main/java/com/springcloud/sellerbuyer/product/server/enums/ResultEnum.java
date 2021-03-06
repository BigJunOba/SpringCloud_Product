package com.springcloud.sellerbuyer.product.server.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {

    PRODUCT_NOT_EXIST(1, "商品不存在"),

    PRODUCT_STOCK_ERROR(2, "库存不正确")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
