package com.sweatTV.mapper;

import com.sweatTV.dto.UserDTO;
import com.sweatTV.dto.request.RegisterUserRequest;
import com.sweatTV.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // RegisterUserRequest -> User

    User toEntity(RegisterUserRequest request);

    // User -> RegisterUserRequest

    RegisterUserRequest toRegisterRequest(User user);

    // User -> UserDTO

    UserDTO toDto(User user);

    List<UserDTO> toDtoList(List<User> users);
}
