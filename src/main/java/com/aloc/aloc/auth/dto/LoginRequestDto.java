package com.aloc.aloc.auth.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String githubId;
    private String password;
}
