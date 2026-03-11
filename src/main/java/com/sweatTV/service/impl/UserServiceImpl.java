package com.sweatTV.service.impl;

import com.sweatTV.dto.UserDTO;
import com.sweatTV.dto.response.MessageResponse;
import com.sweatTV.dto.response.PaginatedResponse;
import com.sweatTV.entity.User;
import com.sweatTV.exception.EmailChangedException;
import com.sweatTV.exception.UserNotFoundException;
import com.sweatTV.mapper.UserMapper;
import com.sweatTV.repository.UserRepository;
import com.sweatTV.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public MessageResponse updateUserProfile(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        boolean emailChanged = false;

        if (userDTO.getUsername() != null && !userDTO.getUsername().trim().isEmpty()){
            user.setUsername(userDTO.getUsername());
        }
        if (userDTO.getEmail() != null && !userDTO.getUsername().trim().isEmpty()){
            if (!user.getEmail().equals(userDTO.getEmail())){
                emailChanged = true;
                user.setEmail(userDTO.getEmail());
            }
        }
        if (userDTO.getPassword() != null && !userDTO.getPassword().trim().isEmpty()){
            user.setPassword(userDTO.getPassword());
        }
        User savedUser = userRepository.save(user);

        if (emailChanged){
            user.setVerificationToken(null);
            userRepository.save(user);
            throw new EmailChangedException("Email updated. Please login again with new email.");
        }

        return new MessageResponse("Profile updated successfully");
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> user = userRepository.findAll();
        return userMapper.toDtoList(user);
    }

    @Override
    public MessageResponse deleteUser(Long id) {
        userRepository.deleteById(id);
        return new MessageResponse("User with id " + id + " deleted successfully");
    }
}
