package com.aloc.aloc.auth.service;

import com.aloc.aloc.auth.dto.LoginRequestDto;
import com.aloc.aloc.auth.dto.LoginResponseDto;
import com.aloc.aloc.auth.dto.RegisterRequestDto;
import com.aloc.aloc.auth.dto.TokenResponseDto;
import com.aloc.aloc.global.jwt.JwtTokenProvider;
import com.aloc.aloc.user.entity.User;
import com.aloc.aloc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

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

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByGithubId(loginRequestDto.getGithubId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if(!user.matchPassword(passwordEncoder, loginRequestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다. ");
        }

        String accessToken = jwtTokenProvider.createToken(user.getGithubId());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getGithubId());
        //아직 jwt 라이브러리 토큰 발급 만들지 X

        user.updateRefreshToken(refreshToken);
        userRepository.save(user);

        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .username(user.getUsername())
                .build();
    }

    public TokenResponseDto refreshToken(String refreshToken) {
        if(!jwtTokenProvider.validateToken(refreshToken)){
            throw new RuntimeException("Invalid refresh token.");
        }

        String githubId = jwtTokenProvider.getGithubId(refreshToken);
        User user = userRepository.findByGithubId(githubId)
                .orElseThrow(()->new RuntimeException("User not found."));

        if(!refreshToken.equals(user.getRefreshToken())){
            throw new RuntimeException("Invalid refresh token.");
        }

        String newAccessToken = jwtTokenProvider.createToken(githubId);
        return new TokenResponseDto(newAccessToken);
    }
}
