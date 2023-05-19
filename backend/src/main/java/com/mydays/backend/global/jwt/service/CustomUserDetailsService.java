package com.mydays.backend.global.jwt.service;

import com.mydays.backend.domain.user.entity.Member;
import com.mydays.backend.domain.user.repository.MemberRepository;
import com.mydays.backend.global.jwt.MemberAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        Member member = memberRepository.findOneWithAuthoritiesByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));

        if(!member.isActivated()) throw new RuntimeException(member.getName() + " -> 활성화되어 있지 않습니다.");
        return new MemberAdapter(member);
    }
}