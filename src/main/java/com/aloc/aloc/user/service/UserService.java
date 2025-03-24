package com.aloc.aloc.user.service;

import com.aloc.aloc.auth.dto.LoginRequestDto;
import com.aloc.aloc.auth.dto.LoginResponseDto;
import com.aloc.aloc.auth.dto.RegisterRequestDto;
import com.aloc.aloc.user.dto.UserResponseDto;
import com.aloc.aloc.user.entity.User;
import com.aloc.aloc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
