package com.study.cleanArch.order.application.port.in;

import com.study.cleanArch.order.domain.Order;

public interface RegisterOrderUseCase {
    public void registerOrder(Order order);
}
