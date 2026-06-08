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
@Schema(description = "User information for user view")
public class UserForUserDto {

    @NotBlank
    @Schema(description = "user's username",example="user550")
    private String username;

    @NotBlank
    @Email
    @Schema(description = "user's email address", example = "user@gmail.com")
    private String email;
}
