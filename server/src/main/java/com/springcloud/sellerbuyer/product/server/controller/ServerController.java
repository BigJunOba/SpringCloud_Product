package com.springcloud.sellerbuyer.product.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: product
 * @description: 商品端作为http server端
 * @author: JunOba
 * @create: 2018-12-16 21:08
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return "this is a product's msg";
    }
}
