package com.aloc.aloc.user.controller;

import com.aloc.aloc.user.dto.LoginRequestDto;
import com.aloc.aloc.user.dto.LoginResponseDto;
import com.aloc.aloc.user.dto.RegisterRequestDto;
import com.aloc.aloc.user.dto.UserResponseDto;
import com.aloc.aloc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto registerRequestDto) {
        userService.register(registerRequestDto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        return ResponseEntity.ok(loginResponseDto);
    }
}
