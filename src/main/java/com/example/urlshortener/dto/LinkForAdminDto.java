package com.example.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LinkForAdminDto {

    Long id;
    String shortUrl;
    String originalUrl;
    String userName;
}
