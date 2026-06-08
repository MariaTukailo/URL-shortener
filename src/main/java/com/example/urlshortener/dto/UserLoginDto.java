package com.example.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "user login information")
public class UserLoginDto {

    @Schema(description = "user's username",example="user550")
    private String username;

    @Schema(description = "user's password",example="user550-6654")
    private String password;
}
