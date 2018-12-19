package com.springcloud.sellerbuyer.product.server.service.impl;

import com.rabbitmq.tools.json.JSONUtil;
import com.springcloud.sellerbuyer.product.common.DecreaseStockInput;
import com.springcloud.sellerbuyer.product.common.ProductInfoOutput;
import com.springcloud.sellerbuyer.product.server.Utils.JsonUtil;
import com.springcloud.sellerbuyer.product.server.dataobject.ProductInfo;
import com.springcloud.sellerbuyer.product.server.enums.ProductStatusEnum;
import com.springcloud.sellerbuyer.product.server.enums.ResultEnum;
import com.springcloud.sellerbuyer.product.server.exception.ProductException;
import com.springcloud.sellerbuyer.product.server.repository.ProductInfoRepository;
import com.springcloud.sellerbuyer.product.server.service.ProductService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return repository.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
    }

//    @Override
//    @Transactional
//    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
//        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
//            Optional<ProductInfo> productInfoOptional = repository.findById(decreaseStockInput.getProductId());
//
//            // 判断商品是否存在
//            if (!productInfoOptional.isPresent()) {
//                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
//            }
//            ProductInfo productInfo = productInfoOptional.get();
//
//            // 库存是否足够
//            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
//            if (result < 0) {
//                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
//            }
//
//            // 修改库存并保存
//            productInfo.setProductStock(result);
//            repository.save(productInfo);
//
//            // 发送MQ消息
//            /**
//             * 假如购物车中有两样商品，如果第一件商品库存够，而第二件商品库存不够时
//             * 这个时候，第一件商品的信息已经发送出去了，
//             * 对数据库不会有什么影响，因为会回滚，
//             * 因此，发送MQ消息的代码片段不能写在for循环内部
//             * 需要对整个购物车的库存信息处理完成之后再发送消息。
//             */
//            ProductInfoOutput output = new ProductInfoOutput();
//            BeanUtils.copyProperties(productInfo, output);
//            amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(output));
//        }
//    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);
        // 发送MQ消息
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e, output);
            return output;
        }).collect(Collectors.toList());
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            Optional<ProductInfo> productInfoOptional = repository.findById(decreaseStockInput.getProductId());

            // 判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();

            // 库存是否足够
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            // 修改库存并保存
            productInfo.setProductStock(result);
            repository.save(productInfo);

            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
