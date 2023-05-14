package com.mydays.backend.dto;

import com.mydays.backend.domain.Diary;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class DiaryForm {

    private Long diaryIdx;
    private String email;
    private String date;
    private String content;
    private String face;
    private String exercise;
    public Diary toEntity(){
        return Diary.builder()
                .diaryIdx(diaryIdx)
                .email(email)
                .date(date)
                .content(content)
                .face(face)
                .exercise(exercise)
                .build();
    }
}
