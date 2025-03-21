package com.aloc.aloc.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    private String username;
    private String githubId;
    private String studentId;
    private String password;
    private String discordId;
    private String notionEmail;
}
