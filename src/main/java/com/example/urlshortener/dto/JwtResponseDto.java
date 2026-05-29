package com.example.urlshortener.dto;

import com.example.urlshortener.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JwtResponseDto {

    private Long id;
    private String token;
    private String type="Bearer";
    private Role role;
    private String email;
    private String username;

}
