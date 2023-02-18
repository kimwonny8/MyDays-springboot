package com.mydays.backend.dto;

import com.mydays.backend.domain.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ImageForm {

    private Long imgIdx;
    private String imgName;
    private String imgOriName;
    private String imgUrl;

    public Image toEntity(){
        return Image.builder()
                .imgIdx(imgIdx)
                .imgName(imgName)
                .imgOriName(imgOriName)
                .imgUrl(imgUrl)
                .build();
    }

    public ImageForm(Image image){
        this.imgIdx = image.getImgIdx();
        this.imgName = image.getImgName();
        this.imgOriName = image.getImgOriName();
        this.imgUrl = image.getImgUrl();
    }
}
