package com.study.cleanArch.member.adapter.out.persistence;

import com.study.cleanArch.member.application.port.out.RegisterMemberPort;
import com.study.cleanArch.member.domain.Member;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements RegisterMemberPort {

    private final EntityManager em;

    @Override
    public void registerMember(Member member) {
        em.persist(new MemberJpaEntity(member.getEmail(),member.getName(),member.getPassword(),member.getAuth()));
    }
}
