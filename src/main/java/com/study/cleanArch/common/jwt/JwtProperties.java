package com.study.cleanArch.common.jwt;

public interface JwtProperties {
    final String SECRET = "nodayst";
    final int SECOND = 1_000;
    final int MIN = 60 * SECOND;
    final int EXPIRATION_TIME = 30 * MIN; // 30분
    final String TOKEN_PREFIX = "Bearer ";
    final String HEADER_STRING = "Authorization";
}
