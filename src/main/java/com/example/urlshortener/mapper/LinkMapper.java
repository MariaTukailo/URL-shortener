package com.example.urlshortener.mapper;

import com.example.urlshortener.dto.LinkForAdminDto;
import com.example.urlshortener.dto.LinkForUserDto;
import com.example.urlshortener.entity.Link;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface LinkMapper {
  LinkForUserDto toLinkForUserDto(Link link);

  @Mapping(target = "userName",source = "user.username")
  LinkForAdminDto LinkForAdminDto(Link link);
}
