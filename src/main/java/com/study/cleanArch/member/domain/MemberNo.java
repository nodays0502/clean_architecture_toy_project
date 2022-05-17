package com.study.cleanArch.member.domain;

import java.util.Objects;
import lombok.Getter;

@Getter
public class MemberNo {
    long value;

    public MemberNo(long value) {
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
        MemberNo memberNo = (MemberNo) o;
        return value == memberNo.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
