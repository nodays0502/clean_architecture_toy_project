package com.study.cleanArch.member.application.port.in;

import com.study.cleanArch.member.domain.Member;
import com.study.cleanArch.member.domain.MemberNo;

public interface GetMemberQuery {
    public Member findByEmail(String email);
    public Member findByNo(MemberNo memberNo);
}
