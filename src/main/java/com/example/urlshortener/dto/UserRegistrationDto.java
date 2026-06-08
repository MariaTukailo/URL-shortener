package com.example.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "user registration information")
public class UserRegistrationDto {

    @Schema(description = "user's username",example="user550")
    private String username;

    @Schema(description = "user's password",example="user550-6654")
    private String password;

    @Schema(description = "user's email address", example = "user@gmail.com")
    private String email;
}
