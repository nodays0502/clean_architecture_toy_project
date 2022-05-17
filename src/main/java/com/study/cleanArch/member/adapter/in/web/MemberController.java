package com.study.cleanArch.member.adapter.in.web;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.study.cleanArch.common.auth.PrincipalDetails;
import com.study.cleanArch.common.jwt.JwtProperties;
import com.study.cleanArch.common.jwt.JwtTokenDto;
import com.study.cleanArch.member.adapter.in.web.dto.LoginRequestDto;
import com.study.cleanArch.member.adapter.in.web.dto.RegisterMemberRequestDto;
import com.study.cleanArch.member.application.port.in.GetMemberQuery;
import com.study.cleanArch.member.application.port.in.RegisterMemberUseCase;
import java.util.Date;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final GetMemberQuery getMemberQuery;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RegisterMemberUseCase registerMemberUsecase;

    @PostMapping("/member/register")
    public ResponseEntity RegisterMember(@Valid @RequestBody RegisterMemberRequestDto registerMemberRequestDto){
        registerMemberUsecase.registerMember(registerMemberRequestDto.of());
        return new ResponseEntity<>(CREATED);
    }

    @PostMapping("/member/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDto loginRequestDto){

        // 유저네임패스워드 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword());

        Authentication authentication =
            authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        String jwtToken = JwtProperties.TOKEN_PREFIX + JWT.create()
            .withSubject(principalDetails.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))
            .withClaim("no", principalDetails.getMember().getMemberNo().getValue())
            .withClaim("email", principalDetails.getMember().getEmail())
            .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        JwtTokenDto jwtTokenDto = new JwtTokenDto(jwtToken);

        return new ResponseEntity<JwtTokenDto>(jwtTokenDto,OK);
    }
}
