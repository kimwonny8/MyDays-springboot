package com.mydays.backend.service;

import com.mydays.backend.domain.Diary;
import com.mydays.backend.dto.DiaryForm;
import com.mydays.backend.exception.DuplicateException;
import com.mydays.backend.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    @Transactional
    public String post(Diary diary) {
        if(!diaryRepository.existsByEmailAndDate(diary.getEmail(), diary.getDate())){
            return diaryRepository.save(diary).getDate();
        } else {
            throw new DuplicateException();
        }
    }

    public List<Diary> diaryList(String email){
        List<Diary> diaryList = diaryRepository.findAllByEmail(email);
        System.out.println("서비스: "+diaryList.size());
        return diaryList;
    }

}
