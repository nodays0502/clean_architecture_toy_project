package com.study.cleanArch.product.application.port.in;

import static org.junit.jupiter.api.Assertions.*;

import com.study.cleanArch.common.domain.Money;
import com.study.cleanArch.product.adapter.out.persistence.ProductJpaEntity;
import com.study.cleanArch.product.domain.Product;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RegisterProductUseCaseTest {
    @Autowired
    private RegisterProductUseCase registerProductUseCase;

    @Autowired
    private EntityManager em;

    @Test
    void registerProductTest() {
        Product product = Product.withOutNo("치킨", new Money(17000), "치킨은 맛있어");
        registerProductUseCase.registerProduct(product);
        Product productByName = em.createQuery(
            "select p from ProductJpaEntity p where p.name =: name",
            ProductJpaEntity.class).setParameter("name", "치킨").getSingleResult().of();
        assertEquals("치킨",productByName.getName());
        assertEquals(17000,productByName.getPrice().getValue());
        assertEquals("치킨은 맛있어",productByName.getDesc());
    }
}