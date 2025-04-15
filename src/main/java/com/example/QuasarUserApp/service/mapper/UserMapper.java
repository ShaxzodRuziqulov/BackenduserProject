/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.09.2024
 * TIME:17:42
 */
package com.example.QuasarUserApp.service.mapper;

import com.example.QuasarUserApp.entity.User;
import com.example.QuasarUserApp.service.dto.RegisterUserDto;
import com.example.QuasarUserApp.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    @Mapping(source = "username", target = "userName")
    UserDto toDto(User user);

    User toUser(RegisterUserDto userDto);
}
