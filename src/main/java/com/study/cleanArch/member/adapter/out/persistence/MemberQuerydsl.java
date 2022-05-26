package com.study.cleanArch.member.adapter.out.persistence;

import static com.study.cleanArch.member.adapter.out.persistence.QMemberJpaEntity.memberJpaEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.cleanArch.exception.member.NotFoundMemberException;
import com.study.cleanArch.member.application.port.out.GetMemberQueryPort;
import com.study.cleanArch.member.domain.Member;
import com.study.cleanArch.member.domain.MemberNo;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
class MemberQuerydsl implements GetMemberQueryPort {

    private final JPAQueryFactory queryFactory;

    public MemberQuerydsl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Member findByEmail(String email) {
        MemberJpaEntity memberJpaEntity = queryFactory
            .select(QMemberJpaEntity.memberJpaEntity)
            .from(QMemberJpaEntity.memberJpaEntity)
            .where(QMemberJpaEntity.memberJpaEntity.email.eq(email))
            .fetchOne();
        if(memberJpaEntity == null){
            throw new NotFoundMemberException();
        }
        return memberJpaEntity.of();
    }

    @Override
    public Member findByNo(MemberNo memberNo) {
        MemberJpaEntity memberJpaEntity = queryFactory
            .select(QMemberJpaEntity.memberJpaEntity)
            .from(QMemberJpaEntity.memberJpaEntity)
            .where(QMemberJpaEntity.memberJpaEntity.no.eq(memberNo.getValue()))
            .fetchOne();
        if(memberJpaEntity == null){
            throw new NotFoundMemberException();
        }
        return memberJpaEntity.of();
    }
}
