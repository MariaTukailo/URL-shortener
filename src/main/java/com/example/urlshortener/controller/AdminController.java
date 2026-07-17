package com.example.urlshortener.controller;

import com.example.urlshortener.dto.LinkForAdminDto;
import com.example.urlshortener.dto.UserForAdminDto;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.service.LinkService;
import com.example.urlshortener.service.UserService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final LinkService linkService;

    @GetMapping("/users")
    public List<UserForAdminDto> findAll() {
        return userService.findAll().stream()
                .map(u -> new UserForAdminDto(u.getId(), u.getRole(), u.getEmail(), u.getUsername()))
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public UserForAdminDto findUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return new UserForAdminDto(user.getId(), user.getRole(), user.getEmail(), user.getUsername());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/users/{id}")
    public UserForAdminDto updateUserById(@PathVariable Long id,
                                          @Size(min = 3, max = 50) @RequestParam(required = false) String username,
                                          @Email @RequestParam(required = false) String email) {
        User user = userService.updateUser(id, email, username);
        return new UserForAdminDto(user.getId(), user.getRole(), user.getEmail(), user.getUsername());
    }

    @GetMapping("/links")
    public List<LinkForAdminDto> findAllLinks() {
        return linkService.findAll().stream()
                .map(link -> new LinkForAdminDto(link.getId(), link.getShortUrl(), link.getOriginalUrl(),
                        link.getUser() != null ? link.getUser().getUsername() : null))
                .collect(Collectors.toList());
    }

    @GetMapping("/links/user/{userId}")
    public List<LinkForAdminDto> findLinksByUserId(@PathVariable Long userId) {
        return linkService.findByUser(userId).stream()
                .map(link -> new LinkForAdminDto(link.getId(), link.getShortUrl(), link.getOriginalUrl(),
                        link.getUser() != null ? link.getUser().getUsername() : null))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/links/{id}")
    public ResponseEntity<Void> deleteLinkById(@PathVariable Long id) {
        linkService.deleteLink(id);
        return ResponseEntity.noContent().build();
    }
}