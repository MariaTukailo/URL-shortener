package com.example.urlshortener.controller;

import com.example.urlshortener.dto.LinkForAdminDto;
import com.example.urlshortener.dto.UserForAdminDto;
import com.example.urlshortener.mapper.LinkMapper;
import com.example.urlshortener.mapper.UserMapper;
import com.example.urlshortener.service.LinkService;
import com.example.urlshortener.service.UserService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final LinkService linkService;
    private final UserMapper userMapper;
    private final LinkMapper linkMapper;

    @GetMapping("/users")
    public List<UserForAdminDto> findAll(){
        return userService.findAll()
                .stream()
                .map(userMapper::toUserForAdminDto)
                .toList();

    }

    @GetMapping("/user/{id}")
    public UserForAdminDto findUserById(@PathVariable Long id){
        return userMapper.toUserForAdminDto(userService.findById(id));

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/user/{id}")
    public UserForAdminDto updateUserById(@PathVariable Long id,
                                          @Size(min = 3, max = 50) @RequestParam(required = false) String username,
                          @Email @RequestParam (required = false)String email)
    {
        return userMapper.toUserForAdminDto(userService.updateUser(id,email,username));

    }

@GetMapping("/links")
    public List<LinkForAdminDto> findAllLinks(){
        return linkService.findAll()
                .stream()
                .map(linkMapper::toLinkForAdminDto)
                .toList();
}

@GetMapping("/link/users/{id}")
    public List<LinkForAdminDto> findLinksByUserId(@PathVariable Long id){
        return linkService.findByUser(id)
                .stream()
                .map(linkMapper::toLinkForAdminDto)
                .toList();

}

@DeleteMapping("/link/{id}")
    public ResponseEntity<Void> deleteLinkById(@PathVariable Long id){
        linkService.deleteLink(id);
        return ResponseEntity.noContent().build();
}

}
