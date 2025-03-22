package com.aloc.aloc.user.service;

import com.aloc.aloc.user.dto.RegisterRequestDto;
import com.aloc.aloc.user.dto.UserResponseDto;
import com.aloc.aloc.user.entity.User;
import com.aloc.aloc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequestDto registerRequestDtoDto) {
        User user = User.builder()
                .username(registerRequestDtoDto.getUsername())
                .githubId(registerRequestDtoDto.getGithubId())
                .studentId(registerRequestDtoDto.getStudentId())
                .password(passwordEncoder.encode(registerRequestDtoDto.getPassword()))
                .discordId(registerRequestDtoDto.getDiscordId())
                .notionEmail(registerRequestDtoDto.getNotionEmail())
                .rank(0)
                .build();

        userRepository.save(user);
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
