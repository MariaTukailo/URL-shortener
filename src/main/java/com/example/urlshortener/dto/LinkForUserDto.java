package com.example.urlshortener.dto;

import com.example.urlshortener.entity.User;
import jakarta.persistence.*;

public class LinkForUserDto {

    String shortUrl;
    String originalUrl;

}
