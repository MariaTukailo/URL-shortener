package com.example.urlshortener.service;

import com.example.urlshortener.dto.JwtResponseDto;
import com.example.urlshortener.dto.UserLoginDto;
import com.example.urlshortener.dto.UserRegistrationDto;
import com.example.urlshortener.entity.Role;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.UserRepository;
import com.example.urlshortener.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

public JwtResponseDto register(UserRegistrationDto request){
    User user = new User();
    user.setEmail(request.getEmail());
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.USER);

    userRepository.save(user);

    String token = jwtService.generateToken(user.getUsername(),user.getRole());
    log.info("User registered: {}", user.getUsername());
   return JwtResponseDto.builder()
            .id(user.getId())
            .token(token)
            .type("Bearer")
            .email(user.getEmail())
            .username(user.getUsername())
            .role(user.getRole())
            .build();
}

public JwtResponseDto login(UserLoginDto request){

    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()));

    User user = userService.findByUsername(request.getUsername());
    String token = jwtService.generateToken(user.getUsername(),user.getRole());

    log.info("User logged in: {}", user.getUsername());
    return JwtResponseDto.builder()
            .id(user.getId())
            .token(token)
            .type("Bearer")
            .email(user.getEmail())
            .username(user.getUsername())
            .role(user.getRole())
            .build();
}
}
