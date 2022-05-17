package com.study.cleanArch.product.application.port.service;

import com.study.cleanArch.product.application.port.in.GetProductQuery;
import com.study.cleanArch.product.application.port.out.GetProductQueryPort;
import com.study.cleanArch.product.domain.Product;
import com.study.cleanArch.product.domain.ProductNo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class GetProductQueryService implements GetProductQuery {

    private final GetProductQueryPort getProductQueryPort;

    @Override
    public Product getProductByNo(ProductNo productNo) {
        return getProductQueryPort.getProductByNo(productNo);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return getProductQueryPort.getProductByName(name);
    }

    @Override
    public List<Product> getProductList() {
        return getProductQueryPort.getProductList();
    }
}
