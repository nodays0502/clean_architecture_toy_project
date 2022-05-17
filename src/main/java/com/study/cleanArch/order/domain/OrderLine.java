package com.study.cleanArch.order.domain;

import com.study.cleanArch.common.domain.Money;
import com.study.cleanArch.product.domain.ProductNo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class OrderLine {

    private Long no;
    private ProductNo productNo;
    private Money price;
    private long quantity;
    private Money amount;

    private OrderLine(Long no, ProductNo productNo, Money price, long quantity) {
        this.no = no;
        this.productNo = productNo;
        this.price = price;
        this.quantity = quantity;
        this.amount = price.multiply(quantity);
    }

    public static OrderLine withOutId(ProductNo productNo, Money price, long quantity) {
        return new OrderLine(null, productNo, price, quantity);
    }

    public static OrderLine withId(long id, ProductNo productNo, Money price, long quantity) {
        return new OrderLine(id, productNo, price, quantity);
    }
}
