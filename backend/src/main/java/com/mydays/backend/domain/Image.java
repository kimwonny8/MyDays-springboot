package com.mydays.backend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgIdx;

    @Column
    private String imgName;
    @Column
    private String imgOriName;
    @Column
    private String imgUrl;

    @Builder
    public Image(Long imgIdx, String imgName, String imgOriName, String imgUrl){
        this.imgIdx=imgIdx;
        this.imgName = imgName;
        this.imgOriName = imgOriName;
        this.imgUrl = imgUrl;
    }
}
