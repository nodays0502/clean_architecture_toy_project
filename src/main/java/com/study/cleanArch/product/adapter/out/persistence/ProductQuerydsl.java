package com.study.cleanArch.product.adapter.out.persistence;


import static com.study.cleanArch.product.adapter.out.persistence.QProductJpaEntity.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.cleanArch.exception.product.NotFoundProductException;
import com.study.cleanArch.product.application.port.out.GetProductQueryPort;
import com.study.cleanArch.product.domain.Product;
import com.study.cleanArch.product.domain.ProductNo;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ProductQuerydsl implements GetProductQueryPort {

    private final JPAQueryFactory queryFactory;

    public ProductQuerydsl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Product getProductByNo(ProductNo productNo) {
        ProductJpaEntity productJpaEntity = queryFactory
            .select(QProductJpaEntity.productJpaEntity)
            .from(QProductJpaEntity.productJpaEntity)
            .where(QProductJpaEntity.productJpaEntity.no.eq(productNo.getValue()))
            .fetchOne();
        if(productJpaEntity == null){
            throw new NotFoundProductException();
        }
        return productJpaEntity.of();
    }

    @Override
    public List<Product> getProductList() {
        List<ProductJpaEntity> productJpaEntityList = queryFactory
            .select(productJpaEntity)
            .from(productJpaEntity)
            .fetch();
        return productJpaEntityList.stream().map(ProductJpaEntity::of).collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductByName(String name){
        List<ProductJpaEntity> productJpaEntityList = queryFactory
            .select(productJpaEntity)
            .from(productJpaEntity)
            .where(productJpaEntity.name.contains(name))
            .fetch();
        return productJpaEntityList.stream().map(ProductJpaEntity::of).collect(Collectors.toList());
    }
}
