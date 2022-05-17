package com.study.cleanArch.common.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

@Data
@AllArgsConstructor
public class JwtTokenDto {
    private String token;
}
