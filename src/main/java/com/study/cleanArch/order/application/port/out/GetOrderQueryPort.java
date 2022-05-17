package com.study.cleanArch.order.application.port.out;

import com.study.cleanArch.member.domain.MemberNo;
import com.study.cleanArch.order.domain.Order;
import com.study.cleanArch.order.domain.OrderNo;
import java.util.List;

public interface GetOrderQueryPort {
    public Order findOrderByNo(OrderNo orderNo);
    public List<Order> findOrderList(MemberNo memberNo);
}
