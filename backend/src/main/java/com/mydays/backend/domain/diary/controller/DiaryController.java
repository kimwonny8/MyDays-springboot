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
@RequestMapping(value="/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/save")
    public String saveDiary(@RequestBody DiaryDto diaryDto){
        return diaryService.saveDiary(diaryDto.toEntity());
    }

    @GetMapping("/list")
    public List<Diary> allList(@RequestParam Map<String, String> params){
        List<Diary> diaryList = diaryService.diaryList(params.get("email"));
        System.out.println(diaryList);
        return diaryList;
    }

    @GetMapping("/select/{diaryIdx}")
    public List<Diary> selectDiary(@PathVariable("diaryIdx") Long diaryIdx){
        System.out.println(diaryIdx);
        List<Diary> diaryList = diaryService.selectDiary(diaryIdx);
        return diaryList;
    }

    @GetMapping("/select/date/{date}")
    public List<Diary> selectDate(@PathVariable("date") String date){
        Long diaryIdx = diaryService.selectDate(date);
        List<Diary> diaryList = diaryService.selectDiary(diaryIdx);
        return diaryList;
    }

    @CrossOrigin
    @DeleteMapping("/delete/{diaryIdx}")
    public void deleteDiary(@PathVariable("diaryIdx") Long diaryIdx){
        System.out.println(diaryIdx);
        diaryService.deleteDiary(diaryIdx);
    }

    @PostMapping("/update/duplChk")
    public String duplChkDate(@RequestBody Map<String, String> params){
        String chk = diaryService.chkDiary(params.get("email"), params.get("date"));
        return chk;
    }

    @CrossOrigin
    @PutMapping("/update/{diaryIdx}")
    public void updateDiary(@PathVariable("diaryIdx") Long diaryIdx, @RequestBody DiaryDto diaryDto) throws Exception {
        System.out.println(diaryDto);
        diaryService.updateDiary(diaryIdx, diaryDto);
    }
}
