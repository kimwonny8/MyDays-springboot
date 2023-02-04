package com.mydays.backend.controller;

import com.mydays.backend.domain.User;
import com.mydays.backend.dto.UserForm;
import com.mydays.backend.repository.UserRepository;
import com.mydays.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/api/user/login")
    public int login(@RequestBody Map<String, String> params) {
        User user = userRepository.findByEmailAndPassword(params.get("email"), params.get("password"));
        if(user != null) {
            return user.getId();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/user/signup")
    public void signup(@RequestBody @Valid UserForm userForm) throws Exception{
        System.out.println("회원가입 왔어");
        userService.signUpUser(userForm);
    }
}
