package com.aloc.aloc.user.dto;

import com.aloc.aloc.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String username;
    private String githubId;
    private Integer rank;

    @Builder
    public UserResponseDto(Long id, String username, String githubId, Integer rank) {
        this.id = id;
        this.username = username;
        this.githubId = githubId;
        this.rank = rank;
    }

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .githubId(user.getGithubId())
                .rank(user.getRank())
                .build();
    }
}
