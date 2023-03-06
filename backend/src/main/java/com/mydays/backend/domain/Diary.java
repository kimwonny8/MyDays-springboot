package com.mydays.backend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Diary {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long diaryIdx;

    @Column(nullable = false, length = 100)
    private String email;

    private String date;

    @Column
    @Lob
    private String content;

    @Column(length = 10)
    private String face;

    @Column
    private String exercise;

    @Builder
    public Diary(Long diaryIdx, String email, String date, String content, String face, String exercise) {
        this.diaryIdx = diaryIdx;
        this.email = email;
        this.date = date;
        this.content = content;
        this.face = face;
        this.exercise = exercise;
    }
}
