package com.sweatTV.mapper;

import com.sweatTV.dto.UserDTO;
import com.sweatTV.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "favoriteMovieId", source = "favorites")
    @Mapping(target = "roles", source = "roles")
    UserDTO toDto(User user);

    @Mapping(target = "favorites", ignore = true)
    @Mapping(target = "roles", expression = "java(Role.valueOf(dto.getRoles()))")
    User toEntity(UserDTO userDTO);

    List<UserDTO> toDtoList(List<User> users);
}
