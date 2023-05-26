package com.mydays.backend.domain.diary.controller;

import com.mydays.backend.domain.diary.entity.Diary;
import com.mydays.backend.domain.diary.dto.DiaryDto;
import com.mydays.backend.domain.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/v1/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("")
    public String saveDiary(@RequestBody DiaryDto diaryDto){
        return diaryService.saveDiary(diaryDto.toEntity());
    }

    @GetMapping("")
    public List<Diary> allList(@RequestParam("email") String email){
        List<Diary> diaryList = diaryService.diaryList(email);
        return diaryList;
    }

    @GetMapping("/{diaryIdx}")
    public List<Diary> selectDiary(@PathVariable("diaryIdx") Long diaryIdx){
        System.out.println(diaryIdx);
        List<Diary> diaryList = diaryService.selectDiary(diaryIdx);
        return diaryList;
    }

    @GetMapping("/date/{date}")
    public List<Diary> selectDate(@PathVariable("date") String date){
        Long diaryIdx = diaryService.selectDate(date);
        List<Diary> diaryList = diaryService.selectDiary(diaryIdx);
        return diaryList;
    }

    @CrossOrigin
    @DeleteMapping("/{diaryIdx}")
    public void deleteDiary(@PathVariable("diaryIdx") Long diaryIdx){
        System.out.println(diaryIdx);
        diaryService.deleteDiary(diaryIdx);
    }

    @CrossOrigin
    @PutMapping("/{diaryIdx}")
    public void updateDiary(@PathVariable("diaryIdx") Long diaryIdx, @RequestBody DiaryDto diaryDto) throws Exception {
        System.out.println(diaryDto);
        diaryService.updateDiary(diaryIdx, diaryDto);
    }

}
