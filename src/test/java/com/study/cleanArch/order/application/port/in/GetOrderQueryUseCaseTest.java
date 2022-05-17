package com.study.cleanArch.order.application.port.in;

import static org.junit.jupiter.api.Assertions.*;

import com.study.cleanArch.common.domain.Money;
import com.study.cleanArch.member.adapter.out.persistence.MemberJpaEntity;
import com.study.cleanArch.member.domain.MemberNo;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class GetOrderQueryUseCaseTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private GetOrderQueryUseCase getOrderQueryUseCase;

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

}