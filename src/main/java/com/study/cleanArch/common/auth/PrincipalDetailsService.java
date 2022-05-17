package com.study.cleanArch.common.auth;

import com.study.cleanArch.member.application.port.in.GetMemberQuery;
import com.study.cleanArch.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final GetMemberQuery getMemberQuery;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        Member memberEntity = getMemberQuery.findByEmail(email);
        System.out.println("loadUserByUsername end");
        System.out.println(memberEntity);
        return new PrincipalDetails(memberEntity);
    }
}
