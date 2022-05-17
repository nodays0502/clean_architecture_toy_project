package com.study.cleanArch.order.domain;

import com.study.cleanArch.common.domain.Money;
import com.study.cleanArch.exception.order.OrderIsDepart;
import com.study.cleanArch.member.domain.MemberNo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {

    private OrderNo no;
    private MemberNo memberNo;
    private List<OrderLine> orderLines;
    private OrderState orderState;
    private long totalPrice;
    private ShippingInfo shippingInfo;

    public static Order withOutNo(MemberNo memberNo, List<OrderLine> orderLines,
        OrderState orderState, ShippingInfo shippingInfo) {
        long totalPrice = 0;
        return new Order(null, memberNo, orderLines, orderState, totalPrice, shippingInfo);
    }

    public static Order withNo(OrderNo no, MemberNo memberNo, List<OrderLine> orderLines,
        OrderState orderState, ShippingInfo shippingInfo) {
        long totalPrice = 0;
        return new Order(no, memberNo, orderLines, orderState, totalPrice, shippingInfo);
    }

    public void cancel() {
        if (this.checkNotShipped()) {
            // 에러 SHIPPING, DELIVERY_COMPLETED
            throw new OrderIsDepart();
        }
        this.orderState = OrderState.CANCEL;
    }

    private boolean checkNotShipped() {
        return this.orderState == OrderState.SHIPPING
            || this.orderState == OrderState.DELIVERY_COMPLETED;
    }

}
