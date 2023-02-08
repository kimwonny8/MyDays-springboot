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

    @GetMapping("/select/{num}")
    public List<Diary> selectDiary(@PathVariable("num") Long num){
//        System.out.println(params.get("num"));
        List<Diary> diaryList = diaryService.selectDiary(num);
        return diaryList;
    }

    @CrossOrigin
    @DeleteMapping("/delete/{num}")
    public void deleteDiary(@PathVariable("num") Long num){
        System.out.println(num);
        diaryService.deleteDiary(num);
    }

    @PostMapping("/update/duplChk")
    public String duplChkDate(@RequestBody Map<String, String> params){
        String chk = diaryService.chkDiary(params.get("email"), params.get("date"));
        return chk;
    }

    @CrossOrigin
    @PutMapping("/update/{num}")
    public void updateDiary(@PathVariable("num") Long num, @RequestBody DiaryForm diaryForm) throws Exception {
        System.out.println(diaryForm);
        diaryService.updateDiary(num, diaryForm);
    }

//    @GetMapping("/update/{num}")
//    public ResponseEntity<?> updateDiary(@PathVariable("num") Long num, DiaryForm diaryForm){
//        System.out.println(diaryForm);
//        try {
//            return new ResponseEntity<DiaryForm>(diaryService.updateDiary(num, diaryForm), HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//        }
//    }
}
