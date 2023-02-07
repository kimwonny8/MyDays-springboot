package com.mydays.backend.dto;

import com.mydays.backend.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserForm {

    private int id;

    @NotEmpty(message = "이메일은 필수 입니다.")
    @Size(max = 100, message = "이메일은 100자리를 초과할 수 없습니다.")
    private String email;
    private String password;

    @NotEmpty(message = "이름은 필수 입니다.")
    @Size(max = 50, message = "이름은 50자리를 초과할 수 없습니다.")
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
