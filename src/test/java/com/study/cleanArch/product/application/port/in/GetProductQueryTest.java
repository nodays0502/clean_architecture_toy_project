package com.study.cleanArch.product.application.port.in;

import static org.junit.jupiter.api.Assertions.*;

import com.study.cleanArch.common.domain.Money;
import com.study.cleanArch.product.adapter.out.persistence.ProductJpaEntity;
import com.study.cleanArch.product.domain.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class GetProductQueryTest {
    @Autowired
    private GetProductQuery getProductQuery;

    @Autowired
    EntityManager em;

    @BeforeEach
    public void init(){
        ProductJpaEntity productJpaEntity1 = new ProductJpaEntity("치킨", 17000, "치킨은 맛있어");
        em.persist(productJpaEntity1);

        ProductJpaEntity productJpaEntity2 = new ProductJpaEntity("만두", 16000, "만두은 맛있어");
        em.persist(productJpaEntity2);

        ProductJpaEntity productJpaEntity3 = new ProductJpaEntity("돈가스", 15000, "돈가스은 맛있어");
        em.persist(productJpaEntity3);

        ProductJpaEntity productJpaEntity4 = new ProductJpaEntity("초밥", 14000, "초밥은 맛있어");
        em.persist(productJpaEntity4);
    }

    @Test
    public void getProductByNoTest(){

        String word = "치킨";
        Product product = getProductQuery.getProductByName(word).get(0);
        Product productByNo = getProductQuery.getProductByNo(product.getProductNo());

        assertEquals(product.getProductNo(), productByNo.getProductNo());
        assertEquals(new Money(17000), productByNo.getPrice());
        assertEquals(product.getDesc(), productByNo.getDesc());
        assertEquals("치킨", productByNo.getName());
    }


    @Test
    public void getProductByNameTest(){
        String word = "치킨";
        List<Product> products = getProductQuery.getProductByName(word);
        assertEquals(1,products.size());
        assertEquals(word, products.get(0).getName());
        assertEquals("치킨은 맛있어", products.get(0).getDesc());
        assertEquals(new Money(17000L),products.get(0).getPrice());
    }

    @Test
    public void getProductListTest(){
        List<Product> products = getProductQuery.getProductList();
        assertEquals(4, products.size());
    }
}