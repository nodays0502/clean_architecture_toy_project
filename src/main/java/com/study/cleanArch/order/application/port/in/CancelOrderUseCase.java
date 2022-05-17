package com.study.cleanArch.order.application.port.in;

import com.study.cleanArch.order.domain.OrderNo;

public interface CancelOrderUseCase {
    public void cancelOrder(OrderNo orderNo);
}
