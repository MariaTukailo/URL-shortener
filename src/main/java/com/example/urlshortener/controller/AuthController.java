package com.example.urlshortener.controller;

import com.example.urlshortener.dto.JwtResponseDto;
import com.example.urlshortener.dto.UserLoginDto;
import com.example.urlshortener.dto.UserRegistrationDto;
import com.example.urlshortener.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

@PostMapping("/register")
    public ResponseEntity<JwtResponseDto> register(@Valid @RequestBody UserRegistrationDto request) {
    return ResponseEntity.status(201).body(authService.register(request));
}

@PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody UserLoginDto request){
    return ResponseEntity.ok(authService.login(request));
}

}
