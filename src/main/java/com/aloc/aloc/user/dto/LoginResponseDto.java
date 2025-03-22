package com.aloc.aloc.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private String username;

    @Builder
    public LoginResponseDto(String accessToken, String refreshToken, String username) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
    }
}
