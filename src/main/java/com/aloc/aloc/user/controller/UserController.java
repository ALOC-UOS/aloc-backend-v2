package com.aloc.aloc.user.controller;

import com.aloc.aloc.auth.dto.LoginRequestDto;
import com.aloc.aloc.auth.dto.LoginResponseDto;
import com.aloc.aloc.auth.dto.RegisterRequestDto;
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

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }


}
