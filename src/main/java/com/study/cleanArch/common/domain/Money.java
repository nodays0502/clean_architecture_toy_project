package com.study.cleanArch.common.domain;

import java.util.Objects;
import lombok.Getter;

@Getter
public class Money {
    private long value;

    public Money(long value) {
        this.value = value;
    }
    public Money multiply(long number){
        return new Money(value *number);
    }

    @Override
    public String toString() {
        return "Money{" +
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
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
