package com.study.cleanArch.member.application.port.service;

import com.study.cleanArch.member.application.port.in.GetMemberQuery;
import com.study.cleanArch.member.application.port.out.GetMemberQueryPort;
import com.study.cleanArch.member.domain.Member;
import com.study.cleanArch.member.domain.MemberNo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMemberQueryService implements GetMemberQuery {
    private final GetMemberQueryPort getMemberQueryPort;

    @Override
    public Member findByEmail(String email) {
        return getMemberQueryPort.findByEmail(email);
    }

    @Override
    public Member findByNo(MemberNo memberNo) {
        return getMemberQueryPort.findByNo(memberNo);
    }
}
