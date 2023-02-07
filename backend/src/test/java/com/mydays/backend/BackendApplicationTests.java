package com.mydays.backend;

import com.mydays.backend.domain.Diary;
import com.mydays.backend.service.DiaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BackendApplicationTests {
    @Autowired
    private DiaryService diaryService;

}
