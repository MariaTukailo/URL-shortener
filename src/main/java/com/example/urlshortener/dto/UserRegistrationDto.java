package com.example.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "user registration information")
public class UserRegistrationDto {

    @NotBlank(message="this field must be filled in")
    @Schema(description = "user's username",example="user550")
    private String username;

    @NotBlank(message="this field must be filled in")
    @Schema(description = "user's password",example="user550-6654")
    private String password;

    @NotBlank(message="this field must be filled in")
    @Email(message="enter the correct email address")
    @Schema(description = "user's email address", example = "user@gmail.com")
    private String email;
}
