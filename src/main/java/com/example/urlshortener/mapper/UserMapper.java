package com.example.urlshortener.mapper;

import com.example.urlshortener.dto.UserForAdminDto;
import com.example.urlshortener.dto.UserForUserDto;
import com.example.urlshortener.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserForAdminDto toUserForAdminDto(User user);
    UserForUserDto toUserForUserDto(User user);
}
