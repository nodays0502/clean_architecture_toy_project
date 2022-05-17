package com.study.cleanArch.order.adapter.out.persistence;

import com.study.cleanArch.member.adapter.out.persistence.MemberJpaEntity;
import com.study.cleanArch.member.domain.MemberNo;
import com.study.cleanArch.order.domain.Order;
import com.study.cleanArch.order.domain.OrderLine;
import com.study.cleanArch.order.domain.OrderNo;
import com.study.cleanArch.order.domain.OrderState;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Getter
@AllArgsConstructor
public class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no")
    private Long no;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<OrderLineJpaEntity> orderLineList;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "shipping_info_id")
    private ShippingInfoJpaEntity shippingInfoJpaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberJpaEntity member;

    public OrderJpaEntity(OrderState orderState,
        ShippingInfoJpaEntity shippingInfoJpaEntity, MemberJpaEntity member) {
        this.orderState = orderState;
        this.shippingInfoJpaEntity = shippingInfoJpaEntity;
        this.member = member;
    }

    public void changeOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public void addOrderLine(OrderLineJpaEntity orderLineJpaEntity) {
        if (orderLineList == null) {
            orderLineList = new ArrayList<>();
        }
        orderLineList.add(orderLineJpaEntity);
        orderLineJpaEntity.setOrder(this);
    }

    public Order of() {
        List<OrderLine> orderLines = orderLineList.stream().map(OrderLineJpaEntity::of)
            .collect(Collectors.toList());
        return Order.withNo(new OrderNo(no), new MemberNo(member.getNo()), orderLines, orderState,
            shippingInfoJpaEntity.of());
    }
}
