package com.example.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Link information for admin view")
public class LinkForAdminDto {

    @NotNull
    @Schema(description = "Link ID",example="1")
    Long id;

    @NotBlank
    @Schema(description = "Short Url",example="http://localhost:8080/r/abc123")
    String shortUrl;

    @NotBlank
    @Schema(description = "Original Url",example="https://ozon.ru/product/123")
    String originalUrl;

    @NotBlank
    @Schema(description = "user's username",example="user550")
    String userName;
}
