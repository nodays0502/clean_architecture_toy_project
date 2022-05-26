package com.study.cleanArch.order.application.port.service;

import com.study.cleanArch.order.application.port.in.CancelOrderUseCase;
import com.study.cleanArch.order.application.port.out.CancelOrderPort;
import com.study.cleanArch.order.application.port.out.GetOrderQueryPort;
import com.study.cleanArch.order.domain.Order;
import com.study.cleanArch.order.domain.OrderNo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class CancelOrderService implements CancelOrderUseCase {
    private final CancelOrderPort cancelOrderPort;
    private final GetOrderQueryPort getOrderQueryPort;

    @Override
    public void cancelOrder(OrderNo orderNo) {
        Order orderByNo = getOrderQueryPort.findOrderByNo(orderNo);
        orderByNo.cancel();
        cancelOrderPort.cancelOrder(orderByNo);
    }
}
