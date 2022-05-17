package com.study.cleanArch.order.adapter.in.web.dto;

import com.study.cleanArch.common.domain.Money;
import com.study.cleanArch.order.domain.Order;
import com.study.cleanArch.order.domain.OrderLine;
import com.study.cleanArch.order.domain.OrderState;
import com.study.cleanArch.order.domain.ShippingInfo;
import com.study.cleanArch.product.domain.ProductNo;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterOrderRequestDto {
    @NotNull
    private List<OrderLineDto> orderLineDtoLists;
    @NotBlank
    private OrderState orderState;
    @NotNull
    private ShippingInfo shippingInfo;

}



