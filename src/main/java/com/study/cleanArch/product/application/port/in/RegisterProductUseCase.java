package com.study.cleanArch.product.application.port.in;

import com.study.cleanArch.product.domain.Product;

public interface RegisterProductUseCase {
    public void registerProduct(Product product);
}
