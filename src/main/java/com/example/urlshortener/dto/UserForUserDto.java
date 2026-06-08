package com.example.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "User information for user view")
public class UserForUserDto {

    @Schema(description = "user's username",example="user550")
    private String username;

    @Schema(description = "user's email address", example = "user@gmail.com")
    private String email;
}
