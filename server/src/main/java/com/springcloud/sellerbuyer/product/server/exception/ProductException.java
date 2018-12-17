package com.springcloud.sellerbuyer.product.server.exception;

import com.springcloud.sellerbuyer.product.server.enums.ResultEnum;
import lombok.Getter;

/**
 * @program: product
 * @description: 异常
 * @author: JunOba
 * @create: 2018-12-17 09:53
 */
@Getter
public class ProductException extends RuntimeException{

    private Integer code;

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
