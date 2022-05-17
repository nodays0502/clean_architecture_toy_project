package com.study.cleanArch.order.adapter.in.web.dto;

import com.study.cleanArch.order.domain.OrderState;
import com.study.cleanArch.order.domain.ShippingInfo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderResponseDto {
    long orderNo;
    List<OrderLineDto> orderLines;
    OrderState orderstate;
    long totalPrice;
    private ShippingInfo shippingInfo;
}
