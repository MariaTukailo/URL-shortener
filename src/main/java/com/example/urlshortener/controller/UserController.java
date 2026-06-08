package com.example.urlshortener.controller;

import com.example.urlshortener.dto.LinkForUserDto;
import com.example.urlshortener.dto.OriginalLinkDto;
import com.example.urlshortener.dto.UserForUserDto;
import com.example.urlshortener.entity.Link;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.mapper.LinkMapper;
import com.example.urlshortener.mapper.UserMapper;
import com.example.urlshortener.service.LinkService;
import com.example.urlshortener.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    private final LinkService linkService;
    private final UserMapper userMapper;
    private final LinkMapper linkMapper;
    @Value("${app.base-url}")
    private String baseUrl;

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByUsername(username);
    }

    @GetMapping("/me")
    public UserForUserDto getProfile() {
        return userMapper.toUserForUserDto(getCurrentUser());
    }

    @PatchMapping("/me")
    public UserForUserDto updateProfile(@Size(min = 3, max = 50) @RequestParam(required = false) String username,
                                     @Email @RequestParam(required = false) String email) {

        User user = getCurrentUser();
       return userMapper.toUserForUserDto(
               userService.updateUser(user.getId(),email,username));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteProfile(){
        userService.deleteUserById(getCurrentUser().getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/links")
    public ResponseEntity<LinkForUserDto> createLink(@Valid @RequestBody OriginalLinkDto request) {
        User user = getCurrentUser();
        Link link = linkService.createLink(request.getOriginalUrl(), user);

        LinkForUserDto dto = linkMapper.toLinkForUserDto(link);
        dto.setShortUrl(baseUrl + "/r/" + link.getShortUrl());

        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping("/links/my")
    public List<LinkForUserDto> getMyLinks(){
        User user=getCurrentUser();
        return linkService.findByUser(user.getId())
                .stream()
                .map(linkMapper::toLinkForUserDto)
                .toList();
    }

    @DeleteMapping("/link/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable Long id){
       linkService.deleteLink(id);
       return ResponseEntity.noContent().build();
    }

}
