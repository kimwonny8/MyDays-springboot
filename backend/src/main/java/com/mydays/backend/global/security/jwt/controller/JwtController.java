package com.mydays.backend.global.security.jwt.controller;

import com.mydays.backend.domain.user.dto.LoginResponseDto;
import com.mydays.backend.global.security.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/jwt")
public class JwtController {

    private final JwtService jwtService;

    @PostMapping(value = "")
    public ResponseEntity<LoginResponseDto> refreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String refreshToken = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refreshToken")) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }
        return jwtService.reToken(refreshToken);
    }
}
