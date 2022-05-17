package com.study.cleanArch.member.adapter.out.persistence;

import com.study.cleanArch.exception.member.DuplicateEmailException;
import com.study.cleanArch.member.application.port.out.RegisterMemberPort;
import com.study.cleanArch.member.domain.Member;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements RegisterMemberPort {

    private final EntityManager em;

    @Override
    public void registerMember(Member member) {
        List<MemberJpaEntity> memberJpaEntityList = em.createQuery(
                "select m from MemberJpaEntity m where m.email =: email", MemberJpaEntity.class)
            .setParameter("email", member.getEmail()).getResultList();
        if (memberJpaEntityList.size() > 0) {
            throw new DuplicateEmailException();
        }
        em.persist(new MemberJpaEntity(member.getEmail(), member.getName(), member.getPassword(),
            member.getAuth()));
    }
}
