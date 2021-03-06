package com.study.cleanArch.order.adapter.out.persistence;

import static com.study.cleanArch.member.adapter.out.persistence.QMemberJpaEntity.memberJpaEntity;
import static com.study.cleanArch.order.adapter.out.persistence.QOrderJpaEntity.orderJpaEntity;
import static com.study.cleanArch.order.adapter.out.persistence.QOrderLineJpaEntity.orderLineJpaEntity;
import static com.study.cleanArch.order.adapter.out.persistence.QShippingInfoJpaEntity.shippingInfoJpaEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.cleanArch.exception.order.NotFoundOrderException;
import com.study.cleanArch.exception.product.NotFoundProductException;
import com.study.cleanArch.member.domain.MemberNo;
import com.study.cleanArch.order.application.port.out.GetOrderQueryPort;
import com.study.cleanArch.order.domain.Order;
import com.study.cleanArch.order.domain.OrderNo;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
class OrderQuerydsl implements GetOrderQueryPort {

    private final JPAQueryFactory queryFactory;

    public OrderQuerydsl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Order findOrderByNo(OrderNo orderNo) {
        OrderJpaEntity orderJpaEntity = queryFactory
            .select(QOrderJpaEntity.orderJpaEntity)
            .from(QOrderJpaEntity.orderJpaEntity, orderLineJpaEntity)
            .where(QOrderJpaEntity.orderJpaEntity.no.eq(orderNo.getValue()))
            .fetchOne();
        if(orderJpaEntity == null){
            throw new NotFoundOrderException();
        }
        return orderJpaEntity.of();
    }

    @Override
    public List<Order> findOrderList(MemberNo memberNo) {
        List<OrderJpaEntity> orderJpaEntities = queryFactory
            .select(orderJpaEntity)
            .from(orderJpaEntity, orderLineJpaEntity, memberJpaEntity, shippingInfoJpaEntity)
            .where(orderJpaEntity.member.no.eq(memberNo.getValue()))
            .fetch();
        List<Order> orders = orderJpaEntities.stream().map(OrderJpaEntity::of).collect(Collectors.toList());
        return orders;
    }

}
