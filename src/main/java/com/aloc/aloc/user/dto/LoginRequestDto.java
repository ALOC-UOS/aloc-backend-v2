package com.aloc.aloc.user.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String githubId;
    private String password;
}
