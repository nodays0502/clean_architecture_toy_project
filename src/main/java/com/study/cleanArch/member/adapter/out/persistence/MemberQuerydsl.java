package com.study.cleanArch.member.adapter.out.persistence;

import static com.study.cleanArch.member.adapter.out.persistence.QMemberJpaEntity.memberJpaEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.cleanArch.member.application.port.out.GetMemberQueryPort;
import com.study.cleanArch.member.domain.Member;
import com.study.cleanArch.member.domain.MemberNo;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class MemberQuerydsl implements GetMemberQueryPort {

    private final JPAQueryFactory queryFactory;

    public MemberQuerydsl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Member findByEmail(String email) {
        return queryFactory
            .select(memberJpaEntity)
            .from(memberJpaEntity)
            .where(memberJpaEntity.email.eq(email))
            .fetchOne()
            .of();
    }

    @Override
    public Member findByNo(MemberNo memberNo) {
        return queryFactory
            .select(memberJpaEntity)
            .from(memberJpaEntity)
            .where(memberJpaEntity.no.eq(memberNo.getValue()))
            .fetchOne()
            .of();
    }
}
