package com.aloc.aloc.user.service;

import com.aloc.aloc.user.dto.LoginRequestDto;
import com.aloc.aloc.user.dto.LoginResponseDto;
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

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByGithubId(loginRequestDto.getGithubId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if(!user.matchPassword(passwordEncoder, loginRequestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다. ");
        }

        String accessToken = "access_token";
        String refreshToken = "refresh_token";
        //아직 jwt 라이브러리 토큰 발급 만들지 X

        user.updateRefreshToken(refreshToken);
        userRepository.save(user);

        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .username(user.getUsername())
                .build();
    }
}
