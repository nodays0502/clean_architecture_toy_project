package com.study.cleanArch.order.adapter.out.persistence;

import com.study.cleanArch.member.adapter.out.persistence.MemberJpaEntity;
import com.study.cleanArch.member.application.port.out.GetMemberQueryPort;
import com.study.cleanArch.member.domain.Member;
import com.study.cleanArch.order.application.port.out.CancelOrderPort;
import com.study.cleanArch.order.application.port.out.RegisterOrderPort;
import com.study.cleanArch.order.domain.Order;
import com.study.cleanArch.order.domain.OrderState;
import com.study.cleanArch.order.domain.ShippingInfo;
import com.study.cleanArch.product.adapter.out.persistence.ProductJpaEntity;
import com.study.cleanArch.product.application.port.in.GetProductQuery;
import com.study.cleanArch.product.application.port.out.GetProductQueryPort;
import com.study.cleanArch.product.domain.ProductNo;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements RegisterOrderPort, CancelOrderPort {

    private final EntityManager em;

    @Override
    public void cancelOrder(Order order) {
        OrderJpaEntity orderJpaEntity = em.find(OrderJpaEntity.class, order.getNo().getValue());
        orderJpaEntity.changeOrderState(OrderState.CANCEL);
    }


    @Override
    public void registerOrder(Order order) {
        MemberJpaEntity memberJpaEntity = em.find(MemberJpaEntity.class,
            order.getMemberNo().getValue());

        ShippingInfo shippingInfo = order.getShippingInfo();
        ShippingInfoJpaEntity shippingInfoJpaEntity = new ShippingInfoJpaEntity(
            shippingInfo.getAddress().getZipCode(),
            shippingInfo.getAddress().getDetailAddress(),
            shippingInfo.getRequestMessage(),
            shippingInfo.getReceiver().getName(), shippingInfo.getReceiver().getPhone());

        OrderJpaEntity orderJpaEntity = new OrderJpaEntity(order.getOrderState(),
            shippingInfoJpaEntity, memberJpaEntity);

        order.getOrderLines().stream()
            .forEach(o -> {
                    ProductJpaEntity productJpaEntity = em.find(ProductJpaEntity.class,
                        o.getProductNo().getValue());
                    orderJpaEntity.addOrderLine(
                        new OrderLineJpaEntity(o.getPrice().getValue(), o.getQuantity(),
                            productJpaEntity));
                }
            );
        System.out.println(orderJpaEntity.getMember().getNo());
        System.out.println(orderJpaEntity.getOrderState());
        System.out.println(orderJpaEntity.getShippingInfoJpaEntity());
        em.persist(orderJpaEntity);
    }

}
