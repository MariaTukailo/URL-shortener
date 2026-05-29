package com.example.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LinkForAdminDto {

    Long id;
    String shortUrl;
    String originalUrl;
    String userName;
}
