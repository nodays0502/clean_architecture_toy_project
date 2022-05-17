package com.study.cleanArch.product.adapter.out.persistence;

import com.study.cleanArch.common.domain.Money;
import com.study.cleanArch.product.domain.Product;
import com.study.cleanArch.product.domain.ProductNo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
@Getter
@AllArgsConstructor
public class ProductJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_no")
    Long no;

    @Column(name = "product_name")
    String name;

    long price;

    String desc;

    public ProductJpaEntity(String name, long price,String desc) {
        this.name = name;
        this.price = price;
        this.desc = desc;
    }

    public Product of(){
        return Product.withNo(new ProductNo(no),name,new Money(price),desc);
    }
}
