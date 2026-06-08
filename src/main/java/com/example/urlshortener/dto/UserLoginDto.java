package com.example.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "user login information")
public class UserLoginDto {

    @NotBlank(message="this field must be filled in")
    @Schema(description = "user's username",example="user550")
    private String username;

    @NotBlank(message="this field must be filled in")
    @Schema(description = "user's password",example="user550-6654")
    private String password;
}
