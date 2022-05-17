package com.study.cleanArch.order.application.port.out;

import com.study.cleanArch.order.domain.Order;
import com.study.cleanArch.order.domain.OrderNo;

public interface CancelOrderPort {
    public void cancelOrder(Order order);
}
