package com.mydays.backend.repository;

import com.mydays.backend.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DiaryRepository extends JpaRepository<Diary, String> {
    boolean existsByDate(String date);
}
