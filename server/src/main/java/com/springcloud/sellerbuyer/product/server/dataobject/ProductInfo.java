package com.springcloud.sellerbuyer.product.server.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class ProductInfo{

    @Id
    private String productId;

    /** 商品名字. */
    private String productName;

    /** 商品单价. */
    private BigDecimal productPrice;

    /** 商品库存. */
    private Integer productStock;

    /** 商品描述. */
    private String productDescription;

    /** 商品小图. */
    private String productIcon;

    /** 商品状态, 0正常1下架. */
    private Integer productStatus;

    /** 商品类目编号. */
    private Integer categoryType;

    /** 商品创建时间. */
    private Date createTime;

    /** 商品修改时间. */
    private Date updateTime;
}
