package com.study.cleanArch.product.application.port.out;

import com.study.cleanArch.product.domain.Product;
import com.study.cleanArch.product.domain.ProductNo;
import java.util.List;

public interface GetProductQueryPort {
    public Product getProductByNo(ProductNo productNo);
    public List<Product> getProductByName(String name);
    public List<Product> getProductList();
}
