package com.study.cleanArch.product.adapter.out.persistence;

import com.study.cleanArch.product.application.port.out.RegisterProductPort;
import com.study.cleanArch.product.domain.Product;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements RegisterProductPort {

    private final EntityManager em;

    @Override
    public void registerProduct(Product product) {
        ProductJpaEntity productJpEntity = new ProductJpaEntity(product.getName(),
            product.getPrice().getValue(), product.getDesc());
        em.persist(productJpEntity);
    }
}
