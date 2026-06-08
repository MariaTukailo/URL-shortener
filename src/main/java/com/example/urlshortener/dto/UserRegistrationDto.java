package com.example.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Объект передачи данных цветов")
public class UserRegistrationDto {

    private String username;
    private String password;
    private String email;
}
