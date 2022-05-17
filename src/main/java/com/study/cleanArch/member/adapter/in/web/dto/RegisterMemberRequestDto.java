package com.study.cleanArch.member.adapter.in.web.dto;

import com.study.cleanArch.member.domain.Member;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMemberRequestDto {

    @NotBlank
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String auth;

    public Member of() {
        return Member.withOutNo(email, name, password, auth);
    }
}
