package com.mydays.backend.domain.user.dto;

import com.mydays.backend.domain.user.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    private Long id;

    private String email;
    private String password;

    private String name;
    private String birth;

    private List<String> roles;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .birth(birth)
                .roles(roles)
                .build();
    }
}
