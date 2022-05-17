package com.study.cleanArch.member.application.port.in;

import static org.junit.jupiter.api.Assertions.*;

import com.study.cleanArch.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RegisterMemberUseCaseTest {
    @Autowired
    private RegisterMemberUseCase registerMemberUseCase;

    @Autowired
    private GetMemberQuery getMemberQuery;

    @Test
    public void registerMemberTest(){
        String email = "test@naver.com";
        String name = "tester";
        String auth = "ROLE_USER";
        Member member = Member.withOutNo(email, name, "pass", auth);
        registerMemberUseCase.registerMember(member);
        Member memberFindByEmail = getMemberQuery.findByEmail(email);
        assertEquals(email,memberFindByEmail.getEmail());
        assertEquals(name,memberFindByEmail.getName());
        assertEquals(auth,memberFindByEmail.getAuth());
    }
}