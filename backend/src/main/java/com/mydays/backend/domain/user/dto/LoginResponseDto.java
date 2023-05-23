package com.mydays.backend.domain.user.dto;

import com.mydays.backend.domain.user.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LoginResponseDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;
}