package com.example.backend.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {

   // @NotEmpty(message = "내용은 필수입니다.")
    String id;

 //   @NotEmpty(message = "내용은 필수입니다.")
    String password;

    String name;

    String birth;
}