package com.example.urlshortener.dto;

import com.example.urlshortener.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Link information for user view")
public class LinkForUserDto {

    @NotBlank
    @Schema(description = "Short Url",example="http://localhost:8080/r/abc123")
    String shortUrl;

    @NotBlank
    @Schema(description = "Original Url",example="https://ozon.ru/product/123")
    String originalUrl;

}
