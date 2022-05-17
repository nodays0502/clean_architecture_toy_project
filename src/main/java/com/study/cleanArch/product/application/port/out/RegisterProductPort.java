package com.study.cleanArch.product.application.port.out;

import com.study.cleanArch.product.domain.Product;

public interface RegisterProductPort {
    public void registerProduct(Product product);
}
