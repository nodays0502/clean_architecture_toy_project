package com.study.cleanArch.product.adapter.in.web.dto;

import com.study.cleanArch.common.domain.Money;
import com.study.cleanArch.product.domain.Product;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterProductRequestDto {

    @NotBlank
    String name;

    @Min(0)
    long price;

    @NotBlank
    String desc;

    public Product of(){
        return Product.withOutNo(name,new Money(price),desc);
    }
}
