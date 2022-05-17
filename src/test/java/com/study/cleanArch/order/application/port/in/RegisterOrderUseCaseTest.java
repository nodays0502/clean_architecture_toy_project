package com.study.cleanArch.order.application.port.in;

import static org.junit.jupiter.api.Assertions.*;

import com.study.cleanArch.common.domain.Money;
import com.study.cleanArch.member.adapter.out.persistence.MemberJpaEntity;
import com.study.cleanArch.member.domain.MemberNo;
import com.study.cleanArch.order.adapter.out.persistence.OrderJpaEntity;
import com.study.cleanArch.order.adapter.out.persistence.OrderLineJpaEntity;
import com.study.cleanArch.order.domain.Address;
import com.study.cleanArch.order.domain.Order;
import com.study.cleanArch.order.domain.OrderLine;
import com.study.cleanArch.order.domain.OrderState;
import com.study.cleanArch.order.domain.Receiver;
import com.study.cleanArch.order.domain.ShippingInfo;
import com.study.cleanArch.product.adapter.out.persistence.ProductJpaEntity;
import com.study.cleanArch.product.domain.ProductNo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RegisterOrderUseCaseTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private RegisterOrderUseCase registerOrderUseCase;


    @BeforeEach
    void init(){
        String email = "test@naver.com";
        String name = "tester";
        String auth = "ROLE_USER";

        MemberJpaEntity member = new MemberJpaEntity(email, name, "pass", auth);
        em.persist(member);

        ProductJpaEntity product = new ProductJpaEntity("치킨",17_000,"치킨은 맛있어");
        em.persist(product);
    }

    @Test
    public void registerOrderTest(){
        String email = "test@naver.com";
        MemberJpaEntity member = em.createQuery("select m from MemberJpaEntity m where m.email =: email",
                MemberJpaEntity.class)
            .setParameter("email", email).getSingleResult();

        ProductJpaEntity product = em.createQuery("select p from ProductJpaEntity p where p.name =: productName",
                ProductJpaEntity.class)
            .setParameter("productName", "치킨").getSingleResult();

        List<OrderLine> orderLines = new ArrayList<>();
        OrderLine orderLine = OrderLine.withOutId(new ProductNo(product.getNo()),
            new Money(product.getPrice()), 1);
        orderLines.add(orderLine);

        OrderState orderState = OrderState.NEW;
        ShippingInfo shippingInfo = new ShippingInfo(new Receiver("받는사람","000-0000-000"),"경비실에 맡겨주세요",new Address("00000","판교"));
        Order order = Order.withOutNo(new MemberNo(member.getNo()),orderLines,orderState,shippingInfo);

        registerOrderUseCase.registerOrder(order);

        List<OrderJpaEntity> orders = em.createQuery(
                "select o from OrderJpaEntity o "
                    +"join o.member m "
                    + "where m.email = :email ",
                OrderJpaEntity.class)
            .setParameter("email", email).getResultList();
        assertEquals(1,orders.size());
        assertEquals(orderState,orders.get(0).getOrderState());
        assertEquals(1,orders.get(0).getOrderLineList().size());


    }
}