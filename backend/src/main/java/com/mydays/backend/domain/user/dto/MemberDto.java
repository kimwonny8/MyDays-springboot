package com.mydays.backend.domain.user.dto;

import com.mydays.backend.domain.user.entity.Authority;
import com.mydays.backend.domain.user.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private Long id;

    private String email;
    private String password;

    private String name;
    private String birth;
    private boolean activated;

    private Set<Authority> authorities;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .birth(birth)
                .activated(activated)
                .authorities(authorities)
                .build();
    }
}
