package com.study.cleanArch.member.application.port.service;

import com.study.cleanArch.member.application.port.in.RegisterMemberUseCase;
import com.study.cleanArch.member.application.port.out.RegisterMemberPort;
import com.study.cleanArch.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterMemberService implements RegisterMemberUseCase {

    private final RegisterMemberPort registerMemberPort;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void registerMember(Member member) {
        member.changePassword(bCryptPasswordEncoder.encode(member.getPassword()));
        registerMemberPort.registerMember(member);
    }
}
