package com.mydays.backend.dto;

import com.mydays.backend.domain.Diary;
import lombok.*;

@Data
@NoArgsConstructor
public class DiaryForm {

    private Long num;
    private String email;
    private String date;
    private String content;
    private String face;
    private String photo;
    private String photo2;
    private String photo3;

    public Diary toEntity(){
        return Diary.builder()
                .num(num)
                .email(email)
                .date(date)
                .content(content)
                .face(face)
                .photo(photo)
                .photo2(photo2)
                .photo3(photo3)
                .build();
    }

    public DiaryForm(Diary diary){
        this.num = diary.getNum();
        this.email = diary.getEmail();
        this.content = diary.getContent();
        this.face = diary.getFace();
        this.photo = diary.getPhoto();
        this.photo2 = diary.getPhoto2();
        this.photo3 = diary.getPhoto3();
    }
}
