package com.study.cleanArch.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Member {

    private MemberNo memberNo;
    private String email;
    private String name;
    private String password;
    private String auth;

    public static Member withOutNo(String email, String name, String password, String auth) {
        return new Member(null, email, name, password, auth);
    }

    public static Member withNo(MemberNo memberNo, String email, String name, String password,
        String auth) {
        return new Member(memberNo, email, name, password, auth);
    }
    public void changePassword(String password){
        this.password = password;
    }
}

