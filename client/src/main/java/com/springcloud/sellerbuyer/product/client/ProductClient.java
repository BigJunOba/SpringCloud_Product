package com.springcloud.sellerbuyer.product.client;

import com.springcloud.sellerbuyer.product.common.DecreaseStockInput;
import com.springcloud.sellerbuyer.product.common.ProductInfoOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @program: product Client
 * @description:
 * @author: JunOba
 * @create: 2018-12-17 16:21
 */
@FeignClient(name = "product", fallback = ProductClient.ProductClientFallback.class)
//@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);


    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

    @Component
    class ProductClientFallback implements ProductClient {
        @Override
        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
            return null;
        }

        @Override
        public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        }
    }
}
