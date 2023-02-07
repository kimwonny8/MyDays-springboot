package com.mydays.backend.controller;

import com.mydays.backend.domain.Diary;
import com.mydays.backend.dto.DiaryForm;
import com.mydays.backend.repository.DiaryRepository;
import com.mydays.backend.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/list")
    public List<Diary> allList(@RequestParam Map<String, String> params){
        //System.out.println(params.get("email"));
        List<Diary> diaryList = diaryService.diaryList(params.get("email"));
        return diaryList;
    }
}
