package com.mydays.backend.domain.user.controller;

import com.mydays.backend.domain.user.dto.MemberDto;
import com.mydays.backend.domain.user.dto.LoginRequestDto;
import com.mydays.backend.domain.user.dto.LoginResponseDto;
import com.mydays.backend.domain.user.service.MemberService;
import com.mydays.backend.global.security.jwt.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto,  HttpServletResponse response) {
        LoginResponseDto loginResponseDto = memberService.login(dto);

        Cookie refreshTokenCookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(false);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setDomain("localhost");
        refreshTokenCookie.setMaxAge(14 * 24 * 60 * 60);
        response.addCookie(refreshTokenCookie);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER,"Bearer "+loginResponseDto.getAccessToken());

        return new ResponseEntity<>(loginResponseDto.getAccessToken(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MemberDto dto) throws Exception {
        memberService.register(dto);
        return new ResponseEntity(HttpStatus.OK);
    }

}