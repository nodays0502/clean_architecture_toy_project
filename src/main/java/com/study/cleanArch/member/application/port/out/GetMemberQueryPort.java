package com.study.cleanArch.member.application.port.out;

import com.study.cleanArch.member.domain.Member;
import com.study.cleanArch.member.domain.MemberNo;

public interface GetMemberQueryPort {
    public Member findByEmail(String email);

    public Member findByNo(MemberNo memberNo);
}
