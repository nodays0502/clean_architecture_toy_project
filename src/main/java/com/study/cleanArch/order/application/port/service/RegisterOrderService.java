package com.study.cleanArch.order.application.port.service;

import com.study.cleanArch.order.application.port.in.RegisterOrderUseCase;
import com.study.cleanArch.order.application.port.out.RegisterOrderPort;
import com.study.cleanArch.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterOrderService implements RegisterOrderUseCase {

    private final RegisterOrderPort registerOrderPort;

    @Override
    public void registerOrder(Order order) {
        registerOrderPort.registerOrder(order);
    }
}
