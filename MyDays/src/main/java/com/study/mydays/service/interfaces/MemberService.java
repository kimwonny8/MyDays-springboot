package com.study.mydays.service.interfaces;

import com.study.mydays.dto.LoginDTO;
import com.study.mydays.dto.SignUpFormDTO;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity signup(SignUpFormDTO formDTO);

    ResponseEntity login(LoginDTO loginDTO);
}
