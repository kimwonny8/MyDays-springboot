package com.mydays.backend.domain.user.service;

import com.mydays.backend.domain.user.dto.MemberDto;
import com.mydays.backend.domain.user.dto.MemberLoginRequestDto;
import com.mydays.backend.domain.user.entity.Member;
import com.mydays.backend.global.exception.*;
import com.mydays.backend.global.jwt.JwtTokenProvider;
import com.mydays.backend.global.jwt.TokenInfo;
import com.mydays.backend.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public TokenInfo login(MemberLoginRequestDto dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        Authentication auth = authenticationManagerBuilder
                .getObject().authenticate(authToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(auth);

        return tokenInfo;
    }

    @Transactional
    public Member register(MemberDto dto) {
        return memberRepository.findByEmail(dto.getEmail())
                .orElseGet(() -> {
                    String encodedPassword = passwordEncoder.encode(dto.getPassword());
                    dto.setPassword(encodedPassword);
                    dto.setRoles(Collections.singletonList("USER"));
                    return memberRepository.save(dto.toEntity());
                });
    }
}