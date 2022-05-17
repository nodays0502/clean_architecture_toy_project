package com.study.cleanArch.member.adapter.in.web;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.study.cleanArch.member.adapter.in.web.dto.RegisterMemberRequestDto;
import com.study.cleanArch.member.application.port.in.GetMemberQuery;
import com.study.cleanArch.member.application.port.in.RegisterMemberUseCase;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final RegisterMemberUseCase registerMemberUsecase;

    @PostMapping("/member/register")
    public ResponseEntity RegisterMember(@Valid @RequestBody RegisterMemberRequestDto registerMemberRequestDto){
        registerMemberUsecase.registerMember(registerMemberRequestDto.of());
        return new ResponseEntity<>(CREATED);
    }
    @GetMapping("/member/info")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity user(){
        return new ResponseEntity<>(OK);
    }
}
