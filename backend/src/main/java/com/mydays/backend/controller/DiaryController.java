package com.mydays.backend.controller;

import com.mydays.backend.dto.DiaryForm;
import com.mydays.backend.repository.DiaryRepository;
import com.mydays.backend.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/diary")
public class DiaryController {

    private final DiaryRepository diaryRepository;
    private final DiaryService diaryService;

    @PostMapping("/post")
    public String post(@RequestBody DiaryForm diaryForm){
        return diaryService.post(diaryForm.toEntity());
    }
}
