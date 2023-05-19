package com.mydays.backend.domain.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;
}