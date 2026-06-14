package com.example.urlshortener.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionDto {
    private String errorName;
    private String message;
    private int code;
    private LocalDateTime timestamp;

}
