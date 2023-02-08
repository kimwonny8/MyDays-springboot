package com.mydays.backend.service;

import com.mydays.backend.domain.Diary;
import com.mydays.backend.dto.DiaryForm;
import com.mydays.backend.exception.DuplicateException;
import com.mydays.backend.exception.NotFoundException;
import com.mydays.backend.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    @Transactional
    public String saveDiary(Diary diary) {
        if(!diaryRepository.existsByEmailAndDate(diary.getEmail(), diary.getDate())){
            diaryRepository.save(diary);
            return "ok";
        } else {
            throw new DuplicateException();
        }
    }

    public List<Diary> diaryList(String email){
        List<Diary> diaryList = diaryRepository.findAllByEmail(email);
        //System.out.println("서비스: "+diaryList.size());
        return diaryList;
    }

    public List<Diary> selectDiary(Long num){
        List<Diary> diaryList = diaryRepository.findAllByNum(num);
        return diaryList;
    }

    @Transactional
    public void deleteDiary(Long num){
        try {
            diaryRepository.deleteByNum(num);
            System.out.println("삭제 완료");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public String chkDiary(String email, String date){
        if(!diaryRepository.existsByEmailAndDate(email, date)){
            return null;
        } else {
            throw new DuplicateException();
        }
    }

    public boolean chkNum(Long num){
        Optional<Diary> diary = diaryRepository.findById(num);

        if(diary.isPresent()){
            return true; // 존재
        }
        else {
            return false;
        }
    }

    @Transactional
    public void updateDiary(Long num, DiaryForm diaryForm) throws Exception {
        if(this.chkNum(num)){
            diaryRepository.save(diaryForm.toEntity());
        }
        else {
            throw new NotFoundException();
        }
    }
}
