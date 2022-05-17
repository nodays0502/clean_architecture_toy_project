package com.study.cleanArch.product.domain;

import java.util.Objects;
import lombok.Getter;

@Getter
public class ProductNo {
    long value;

    public ProductNo(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProductNo{" +
            "value=" + value +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductNo productNo = (ProductNo) o;
        return value == productNo.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
