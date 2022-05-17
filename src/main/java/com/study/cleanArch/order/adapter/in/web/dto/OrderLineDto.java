package com.study.cleanArch.order.adapter.in.web.dto;

import com.study.cleanArch.common.domain.Money;
import com.study.cleanArch.order.domain.OrderLine;
import com.study.cleanArch.product.domain.ProductNo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDto {

    private long productNo;
    private long price;
    private long quantity;

    public OrderLine of() {
        return OrderLine.withOutId(new ProductNo(productNo), new Money(price), quantity);
    }
}