package com.study.cleanArch.member.adapter.out.persistence;

import com.study.cleanArch.member.domain.Member;
import com.study.cleanArch.member.domain.MemberNo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Getter
@AllArgsConstructor
public class MemberJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long no;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private String auth;

    public MemberJpaEntity(String email, String name, String password,String auth) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.auth = auth;
    }
    public Member of(){
        return Member.withNo(new MemberNo(no),email,name,password,auth);
    }
}
