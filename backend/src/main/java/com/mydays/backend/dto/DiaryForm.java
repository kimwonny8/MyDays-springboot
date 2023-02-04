package com.mydays.backend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;

@Getter
@Setter
public class DiaryForm {

    private String email;
    private String date;
    private String content;
    private String face;
    private String photo;
    private String photo2;
    private String photo3;
}
