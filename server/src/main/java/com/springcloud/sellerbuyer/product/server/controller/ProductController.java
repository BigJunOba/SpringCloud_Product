package com.springcloud.sellerbuyer.product.server.controller;

import com.springcloud.sellerbuyer.product.common.DecreaseStockInput;
import com.springcloud.sellerbuyer.product.common.ProductInfoOutput;
import com.springcloud.sellerbuyer.product.server.Utils.ResultVOUtil;
import com.springcloud.sellerbuyer.product.server.ViewObject.ProductInfoVO;
import com.springcloud.sellerbuyer.product.server.ViewObject.ProductVO;
import com.springcloud.sellerbuyer.product.server.ViewObject.ResultVO;
import com.springcloud.sellerbuyer.product.server.dataobject.ProductCategory;
import com.springcloud.sellerbuyer.product.server.dataobject.ProductInfo;
import com.springcloud.sellerbuyer.product.server.service.CategoryService;
import com.springcloud.sellerbuyer.product.server.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO getList() {

        // 1.查询所有的商家商品(千万不能把数据库查询放到for循环里面)
        List<ProductInfo> productInfoList = productService.findUpAll();

        // 2.查询类目(一次性查询)(千万不能把数据库查询放到for循环里面)
        // 精简方法(lambda表达式)
        List<Integer> categoryTypeList = productInfoList.stream().
                map(e -> e.getCategoryType()).
                collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    // 将productInfo的值拷贝到productInfoVO
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }

    /**
     * 获取商品列表给订单服务用
     * @param productIdList 购物车里的商品Id列表
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return productService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productService.decreaseStock(decreaseStockInputList);
    }
}
