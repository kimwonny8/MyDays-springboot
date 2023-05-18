package com.mydays.backend.domain.user.controller;

import com.mydays.backend.domain.user.dto.MemberDto;
import com.mydays.backend.domain.user.dto.MemberLoginRequestDto;
import com.mydays.backend.global.jwt.TokenInfo;
import com.mydays.backend.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public TokenInfo login(@RequestBody MemberLoginRequestDto dto) {
        TokenInfo tokenInfo = memberService.login(dto);
        return tokenInfo;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MemberDto dto) throws Exception{
//        System.out.println("dto.getEmail() = " + dto.getEmail());
        memberService.register(dto);
        return new ResponseEntity(HttpStatus.OK);
    }
}