package com.study.cleanArch.product.adapter.in.web.dto;

import com.study.cleanArch.product.domain.Product;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class GetProductResponseDto {

    long no;
    String name;
    long price;
    String desc;

    public GetProductResponseDto(long no, String name, long price, String desc) {
        this.no = no;
        this.name = name;
        this.price = price;
        this.desc = desc;
    }

    public static GetProductResponseDto productToGetProductDto(Product product) {
        return new GetProductResponseDto(product.getProductNo().getValue(), product.getName(),
            product.getPrice().getValue(), product.getDesc());
    }
}
