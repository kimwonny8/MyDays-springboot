package com.mydays.backend.repository;

import com.mydays.backend.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DiaryRepository extends JpaRepository<Diary, Long> {
    boolean existsByEmailAndDate(String email,String date);

    List<Diary> findAllByEmail(String email);
    find
}
