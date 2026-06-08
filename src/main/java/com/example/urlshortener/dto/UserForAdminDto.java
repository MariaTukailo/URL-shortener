package com.example.urlshortener.dto;
import com.example.urlshortener.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "User information for admin view")
public class UserForAdminDto {

    @Schema(description = "Link ID",example="1")
    private Long id;

    @Schema(description = "user's role", example = "USER")
    private Role role;

    @Schema(description = "user's email address", example = "user@gmail.com")
    private String email;

    @Schema(description = "user's username",example="user550")
    private String username;

}

