package com.study.cleanArch.product.adapter.in.web;

import com.study.cleanArch.product.adapter.in.web.dto.GetProductResponseDto;
import com.study.cleanArch.product.adapter.in.web.dto.RegisterProductRequestDto;
import com.study.cleanArch.product.application.port.in.GetProductQuery;
import com.study.cleanArch.product.application.port.in.RegisterProductUseCase;
import com.study.cleanArch.product.domain.Product;
import com.study.cleanArch.product.domain.ProductNo;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final GetProductQuery getProductQuery;
    private final RegisterProductUseCase registerProductUseCase;

    @GetMapping("/product/{productNo}")
    public ResponseEntity getProductByNo(@PathVariable long productNo) {
        Product productByNo = getProductQuery.getProductByNo(new ProductNo(productNo));
        return new ResponseEntity<GetProductResponseDto>(
            GetProductResponseDto.productToGetProductDto(productByNo),
            OK);
    }

    @GetMapping("/product/search/{name}")
    public ResponseEntity getProductByName(@PathVariable String name) {
        List<Product> productByName = getProductQuery.getProductByName(name);

        return new ResponseEntity<List<GetProductResponseDto>>(
            productByName.stream().map(GetProductResponseDto::productToGetProductDto)
                .collect(Collectors.toList()),
            OK);
    }

    @GetMapping("/products")
    public ResponseEntity getProductList() {
        List<Product> productList = getProductQuery.getProductList();
        return new ResponseEntity<List<GetProductResponseDto>>(
            productList.stream().map(GetProductResponseDto::productToGetProductDto)
                .collect(Collectors.toList()),
            OK);
    }

    @PostMapping("/product")
    public ResponseEntity registerProduct(@RequestBody @Valid RegisterProductRequestDto registerProductRequestDto) {
        registerProductUseCase.registerProduct(registerProductRequestDto.of());
        return new ResponseEntity<>(CREATED);
    }
}
