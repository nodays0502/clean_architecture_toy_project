package com.study.cleanArch.product.domain;


import com.study.cleanArch.common.domain.Money;
import lombok.Getter;

@Getter
public class Product {

    private ProductNo productNo;
    private String name;
    private Money price;
    private String desc;

    private Product(ProductNo productNo, String name, Money price, String desc) {
        this.productNo = productNo;
        this.name = name;
        this.price = price;
        this.desc = desc;
    }

    public static Product withOutNo(String name, Money price, String desc) {
        return new Product(null, name, price, desc);
    }

    public static Product withNo(ProductNo productNo, String name, Money price, String desc) {
        return new Product(productNo, name, price, desc);
    }

}
