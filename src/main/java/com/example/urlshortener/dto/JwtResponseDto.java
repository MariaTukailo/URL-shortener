package com.example.urlshortener.dto;

import com.example.urlshortener.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "Reply with a JWT token after logging in or registering")
public class JwtResponseDto {

    @NotNull
    @Schema(description = "User ID", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "API Access Token", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;


    @Schema(description = "token type", example = "Bearer")
    private String type="Bearer";

    @Schema(description = "user's role", example = "USER")
    private Role role;

    @NotBlank
    @Email
    @Schema(description = "user's email address", example = "user@gmail.com")
    private String email;

    @NotBlank
    @Schema(description = "user's username", example = "user550")
    private String username;

}
