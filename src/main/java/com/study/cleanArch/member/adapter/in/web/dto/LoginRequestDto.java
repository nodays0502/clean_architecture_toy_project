package com.study.cleanArch.member.adapter.in.web.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotBlank
    String email;
    @NotBlank
    String password;
}
