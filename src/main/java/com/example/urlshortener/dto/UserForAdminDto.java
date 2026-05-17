package com.example.urlshortener.dto;
import com.example.urlshortener.entity.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserForAdminDto {

    private Long id;
    private Role role;
    private String email;
    private String username;

}

