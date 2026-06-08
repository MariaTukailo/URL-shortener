package com.example.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Request to shorten a URL")
public class OriginalLinkDto {

    @NotBlank(message="this field must be filled in")
    @Schema(description = "Original Url",example="https://ozon.ru/product/123")
    String originalUrl;
}
