package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    // 회원가입
    @RequestMapping(value = "/user/save")
    public String createJson(@Valid @RequestBody UserForm form, BindingResult bindingResult) {
        log.info("Post : user Save");

        return validation(form, bindingResult);
    }

    // user 목록
    @RequestMapping(value = "/user/all")
    public List<User> list() {
        log.info("Get : user List");

        return userService.findAll();
    }

    // user 로그인
    @RequestMapping(value = "/user/login")
    public void login() {
        log.info("Post: chkIdPw");
    }


    // 요청 파라미터 validation 체크
    private String validation(@Valid @RequestBody UserForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "bindingResult error";
        }

        User user = new User();
        user.setId(form.getId());
        user.setPassword(form.getPassword());
        user.setName(form.getName());
        user.setBirth(form.getBirth());
        //user.setPassword(passwordEncoder.encode(form.getPassword()));

        userService.save(user);

        return "ok";
    }

}
