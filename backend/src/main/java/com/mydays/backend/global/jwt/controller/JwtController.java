package com.mydays.backend.global.jwt.controller;

import com.mydays.backend.domain.user.dto.LoginResponseDto;
import com.mydays.backend.global.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/jwt")
public class JwtController {

    private final JwtService jwtService;

    @PostMapping(value = "", headers = "Authorization-refresh")
    public ResponseEntity<LoginResponseDto> refreshToken(@RequestHeader("Authorization-refresh") String refreshToken) {
//        System.out.println("refreshToken = " + refreshToken);
        String token = refreshToken.substring("Bearer ".length());
        return jwtService.reToken(token);
    }
}
