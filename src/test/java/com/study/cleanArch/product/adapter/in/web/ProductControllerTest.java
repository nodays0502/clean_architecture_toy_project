package com.study.cleanArch.product.adapter.in.web;

import static org.junit.jupiter.api.Assertions.*;

import com.study.cleanArch.product.adapter.out.persistence.ProductJpaEntity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductController productController;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void init(){

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
    void getProductListTest() throws Exception {
        mvc.perform(get("/api/products"))
            .andExpect(status().isOk());
    }
}