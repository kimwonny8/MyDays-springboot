package com.mydays.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/image")
public class ImageController {

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Object addImage(@RequestParam("diaryImage") MultipartFile img){
        System.out.println(img.getOriginalFilename());
        return null;
    }
}
