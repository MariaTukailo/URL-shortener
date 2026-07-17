package com.example.urlshortener.controller;

import com.example.urlshortener.dto.LinkForUserDto;
import com.example.urlshortener.dto.OriginalLinkDto;
import com.example.urlshortener.dto.UserForUserDto;
import com.example.urlshortener.entity.Link;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.service.LinkService;
import com.example.urlshortener.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final LinkService linkService;

    @Value("${app.base-url}")
    private String baseUrl;

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUsername(username);
    }

    @GetMapping("/me")
    public UserForUserDto getProfile() {
        User user = getCurrentUser();
        return new UserForUserDto(user.getUsername(), user.getEmail());
    }

    @PatchMapping("/me")
    public UserForUserDto updateProfile(@Size(min = 3, max = 50) @RequestParam(required = false) String username,
                                        @Email @RequestParam(required = false) String email) {
        User user = getCurrentUser();
        User updated = userService.updateUser(user.getId(), email, username);
        return new UserForUserDto(updated.getUsername(), updated.getEmail());
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteProfile() {
        userService.deleteUserById(getCurrentUser().getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/links")
    public ResponseEntity<LinkForUserDto> createLink(@Valid @RequestBody OriginalLinkDto request) {
        System.out.println(">>> 1. createLink called");
        User user = getCurrentUser();
        System.out.println(">>> 2. createLink called");
        Link link = linkService.createLink(request.getOriginalUrl(), user);
        LinkForUserDto dto = new LinkForUserDto(link.getId(),
                baseUrl + "/r/" + link.getShortUrl(),
                link.getOriginalUrl()
        );
        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping("/links/my")
    public List<LinkForUserDto> getMyLinks() {
        User user = getCurrentUser();
        return linkService.findByUser(user.getId()).stream()
                .map(link -> new LinkForUserDto(
                        link.getId(),
                        baseUrl + "/r/" + link.getShortUrl(),
                        link.getOriginalUrl()
                ))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/links/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable Long id) {
        linkService.deleteLink(id);
        return ResponseEntity.noContent().build();
    }
}