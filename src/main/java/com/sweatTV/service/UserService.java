package com.sweatTV.service;

import com.sweatTV.dto.UserDTO;
import com.sweatTV.dto.response.MessageResponse;

import java.util.List;

public interface UserService {

    UserDTO getUserById(Long id);

    MessageResponse updateUserProfile(Long id, UserDTO userDTO);

   List<UserDTO> getAllUsers();

    MessageResponse deleteUser(Long id);

}
