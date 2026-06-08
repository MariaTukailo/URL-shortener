package com.example.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Link information for admin view")
public class LinkForAdminDto {

    @Schema(description = "Link ID",example="1")
    Long id;

    @Schema(description = "Short Url",example="http://localhost:8080/r/abc123")
    String shortUrl;

    @Schema(description = "Original Url",example="https://ozon.ru/product/123")
    String originalUrl;

    @Schema(description = "user's username",example="user550")
    String userName;
}
