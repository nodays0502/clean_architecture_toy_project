package com.study.cleanArch.member.application.port.out;

import com.study.cleanArch.member.domain.Member;

public interface RegisterMemberPort {
    public void registerMember(Member member);
}
