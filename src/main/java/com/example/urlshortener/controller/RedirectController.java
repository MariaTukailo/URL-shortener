package com.example.urlshortener.controller;

import com.example.urlshortener.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class RedirectController {
    private final LinkService linkService;

    @GetMapping("/r/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        String originalUrl = linkService.findByShortUrl(shortUrl).getOriginalUrl();
        return ResponseEntity.status(302)
                .location(URI.create(originalUrl))
                .build();
    }

}
