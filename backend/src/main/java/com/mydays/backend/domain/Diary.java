package com.mydays.backend.domain;

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
}
