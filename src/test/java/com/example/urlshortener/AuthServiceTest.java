package com.example.urlshortener.service;

import com.example.urlshortener.dto.JwtResponseDto;
import com.example.urlshortener.dto.UserLoginDto;
import com.example.urlshortener.dto.UserRegistrationDto;
import com.example.urlshortener.entity.Role;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.UserRepository;
import com.example.urlshortener.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;


    @Test
    void shouldRegisterUser() {
        UserRegistrationDto request = new UserRegistrationDto();
        request.setUsername("alex");
        request.setEmail("alex@mail.com");
        request.setPassword("123");

        when(passwordEncoder.encode("123")).thenReturn("hashed_123");
        when(jwtService.generateToken("alex", Role.USER)).thenReturn("jwt-token");

        JwtResponseDto response = authService.register(request);

        assertNotNull(response);
        assertEquals("alex", response.getUsername());
        assertEquals("alex@mail.com", response.getEmail());
        assertEquals("jwt-token", response.getToken());
        assertEquals(Role.USER, response.getRole());
        verify(userRepository).save(any(User.class));
    }


    @Test
    void shouldLoginUser() {
        UserLoginDto request = new UserLoginDto();
        request.setUsername("alex");
        request.setPassword("123");

        User user = new User();
        user.setId(1L);
        user.setUsername("alex");
        user.setEmail("alex@mail.com");
        user.setRole(Role.USER);

        when(userService.findByUsername("alex")).thenReturn(user);
        when(jwtService.generateToken("alex", Role.USER)).thenReturn("jwt-token");

        JwtResponseDto response = authService.login(request);

        assertNotNull(response);
        assertEquals("alex", response.getUsername());
        assertEquals("jwt-token", response.getToken());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void shouldThrowWhenBadCredentials() {
        UserLoginDto request = new UserLoginDto();
        request.setUsername("alex");
        request.setPassword("wrong");

        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        assertThrows(BadCredentialsException.class, () -> {
            authService.login(request);
        });
    }
}
