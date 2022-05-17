package com.study.cleanArch.order.adapter.out.persistence;

import com.study.cleanArch.common.domain.Money;
import com.study.cleanArch.order.domain.OrderLine;
import com.study.cleanArch.product.adapter.out.persistence.ProductJpaEntity;
import com.study.cleanArch.product.domain.ProductNo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_line")
@Getter
@AllArgsConstructor
public class OrderLineJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderJpaEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;

    private long quantity;

    private long price;

    public OrderLineJpaEntity(long quantity, long price,ProductJpaEntity product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public OrderLine mapToOrderLine(){
        return OrderLine.withId(this.no,new ProductNo(this.product.getNo()),new Money(price),this.quantity);
    }

    public void setOrder(OrderJpaEntity order) {
        this.order = order;
    }
    public OrderLine of(){
        return OrderLine.withId(no,new ProductNo(product.getNo()),new Money(price),quantity);
    }
}
