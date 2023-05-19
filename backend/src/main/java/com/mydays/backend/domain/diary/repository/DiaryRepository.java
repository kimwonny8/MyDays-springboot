package com.mydays.backend.domain.diary.repository;

import com.mydays.backend.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DiaryRepository extends JpaRepository<Diary, Long> {
    boolean existsByEmailAndDate(String email,String date);

    List<Diary> findAllByDate(String date);
    List<Diary> findAllByEmail(String email);
    List<Diary> findAllByDiaryIdx(Long diaryIdx);
    void deleteByDiaryIdx(Long diaryIdx);
}
