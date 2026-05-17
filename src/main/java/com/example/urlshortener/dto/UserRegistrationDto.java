package com.example.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    private String username;
    private String password;
    private String email;
}
