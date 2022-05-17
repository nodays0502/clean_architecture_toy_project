package com.study.cleanArch.order.domain;

import java.util.Objects;
import lombok.Getter;

@Getter
public class OrderNo {
    long value;

    public OrderNo(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderNo orderNo = (OrderNo) o;
        return value == orderNo.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
