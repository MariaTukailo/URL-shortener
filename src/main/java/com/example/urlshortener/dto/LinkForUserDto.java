package com.example.urlshortener.dto;

import com.example.urlshortener.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LinkForUserDto {

    String shortUrl;
    String originalUrl;

}
