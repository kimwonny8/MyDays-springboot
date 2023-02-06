package com.mydays.backend.domain;

import com.mydays.backend.dto.DiaryForm;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
public class Diary {

    @Column(nullable = false, length = 100)
    private String email;

    @Id
    private String date;

    @Column
    @Lob
    private String content;

    @Column(length = 10)
    private String face;

    @Column(length = 100)
    private String photo;

    @Column(length = 100)
    private String photo2;

    @Column(length = 100)
    private String photo3;

    @Builder
    public Diary(String email, String date, String content, String face, String photo, String photo2, String photo3) {
        this.email = email;
        this.date = date;
        this.content = content;
        this.face = face;
        this.photo = photo;
        this.photo2 = photo2;
        this.photo3 = photo3;
    }

    public Diary() {

    }
}
