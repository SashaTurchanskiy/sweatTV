package com.sweatTV.dto;

import com.sweatTV.entity.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Email should be valid")
    private String email;

    private Set<String> roles;

    private Set<Long> favoriteMovieId;
}
