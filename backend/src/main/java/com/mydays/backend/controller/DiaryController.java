package com.mydays.backend.controller;

import com.mydays.backend.domain.Diary;
import com.mydays.backend.dto.DiaryForm;
import com.mydays.backend.service.DiaryService;
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
    public String saveDiary(@RequestBody DiaryForm diaryForm){
        return diaryService.saveDiary(diaryForm.toEntity());
    }

    @GetMapping("/list")
    public List<Diary> allList(@RequestParam Map<String, String> params){
        //System.out.println(params.get("email"));
        List<Diary> diaryList = diaryService.diaryList(params.get("email"));
        return diaryList;
    }

    @GetMapping("/select/{diaryIdx}")
    public List<Diary> selectDiary(@PathVariable("diaryIdx") Long diaryIdx){
        System.out.println(diaryIdx);
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
    public void updateDiary(@PathVariable("diaryIdx") Long diaryIdx, @RequestBody DiaryForm diaryForm) throws Exception {
        System.out.println(diaryForm);
        diaryService.updateDiary(diaryIdx, diaryForm);
    }
}
