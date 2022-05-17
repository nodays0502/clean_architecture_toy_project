package com.study.cleanArch.product.application.port.service;

import com.study.cleanArch.product.application.port.in.RegisterProductUseCase;
import com.study.cleanArch.product.application.port.out.RegisterProductPort;
import com.study.cleanArch.product.domain.Product;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterProductService implements RegisterProductUseCase {

    private final RegisterProductPort registerProductPort;

    @Override
    public void registerProduct(Product product){
        registerProductPort.registerProduct(product);
    }
}
