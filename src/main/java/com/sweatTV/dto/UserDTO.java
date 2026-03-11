package com.sweatTV.dto;

import com.sweatTV.entity.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    private Long id;

    //@NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Email should be valid")
    private String email;

    private String password;

    private String roles;

    private String verificationToken;

    private String verificationTokenExpiry;

    private Set<Long> favoriteMovieId;
}
