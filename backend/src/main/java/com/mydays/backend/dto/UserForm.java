package com.mydays.backend.dto;

import com.mydays.backend.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserForm {

    private int id;

    private String email;
    private String password;

    private String name;
    private String birth;

    public User toEntity(){
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .birth(birth)
                .build();
    }
}
