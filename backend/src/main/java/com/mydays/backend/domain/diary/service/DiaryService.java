package com.mydays.backend.domain.diary.service;

import com.mydays.backend.domain.diary.entity.Diary;
import com.mydays.backend.domain.diary.dto.DiaryDto;
import com.mydays.backend.global.exception.DuplicateException;
import com.mydays.backend.global.exception.NotFoundException;
import com.mydays.backend.domain.diary.repository.DiaryRepository;
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

    public List<Diary> selectDiary(Long diaryIdx){
        List<Diary> diaryList = diaryRepository.findAllByDiaryIdx(diaryIdx);
        return diaryList;
    }

    public Long selectDate(String date){
        List<Diary> diaryList = diaryRepository.findAllByDate(date);
        Long idx = diaryList.get(0).getDiaryIdx();
//        System.out.println(idx);
        return idx;
    }

    @Transactional
    public void deleteDiary(Long diaryIdx){
        try {
            diaryRepository.deleteByDiaryIdx(diaryIdx);
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

    public boolean chkdiaryIdx(Long diaryIdx){
        Optional<Diary> diary = diaryRepository.findById(diaryIdx);

        if(diary.isPresent()){
            return true; // 존재
        }
        else {
            return false;
        }
    }

    @Transactional
    public void updateDiary(Long diaryIdx, DiaryDto diaryDto) throws Exception {
        if(this.chkdiaryIdx(diaryIdx)){
            diaryRepository.save(diaryDto.toEntity());
        }
        else {
            throw new NotFoundException();
        }
    }
}
