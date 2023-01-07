package com.example.backend.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoForm {

    @NotEmpty(message = "내용은 필수입니다.")
    private String item;

    private String date;

    private boolean completed;

    private String time;
}