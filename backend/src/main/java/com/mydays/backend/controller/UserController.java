package com.mydays.backend.controller;

import com.mydays.backend.domain.User;
import com.mydays.backend.repository.UserRepository;
import com.mydays.backend.service.JwtService;
import com.mydays.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    JwtService jwtService;

    @PostMapping("/api/user/login")
    public int login(@RequestBody Map<String, String> params) {
        User user = userRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

        if(user != null) {
            return user.getId();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
