package com.mydays.backend.dto;

import com.mydays.backend.domain.Diary;
import lombok.*;

@Data
@NoArgsConstructor
public class DiaryForm {

    private String email;
    private String date;
    private String content;
    private String face;
    private String photo;
    private String photo2;
    private String photo3;

    public Diary toEntity(){
        return Diary.builder()
                .email(email)
                .date(date)
                .content(content)
                .face(face)
                .photo(photo)
                .photo2(photo2)
                .photo3(photo3)
                .build();
    }
}
