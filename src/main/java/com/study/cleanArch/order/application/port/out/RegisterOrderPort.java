package com.study.cleanArch.order.application.port.out;

import com.study.cleanArch.order.domain.Order;

public interface RegisterOrderPort {
    public void registerOrder(Order order);
}
