package com.study.cleanArch.order.application.port.service;

import com.study.cleanArch.member.domain.MemberNo;
import com.study.cleanArch.order.application.port.in.GetOrderQueryUseCase;
import com.study.cleanArch.order.application.port.out.GetOrderQueryPort;
import com.study.cleanArch.order.domain.Order;
import com.study.cleanArch.order.domain.OrderNo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class GetOrderQueryService implements GetOrderQueryUseCase {
    private final GetOrderQueryPort getOrderQueryPort;
    @Override
    public Order findOrderByNo(OrderNo orderNo) {
        return getOrderQueryPort.findOrderByNo(orderNo);
    }

    @Override
    public List<Order> findOrderList(MemberNo memberNo) {
        return getOrderQueryPort.findOrderList(memberNo);
    }
}
