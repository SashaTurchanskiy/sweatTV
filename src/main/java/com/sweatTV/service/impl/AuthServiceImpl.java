package com.sweatTV.service.impl;

import com.sweatTV.dto.request.LoginRequest;
import com.sweatTV.dto.request.RegisterUserRequest;
import com.sweatTV.dto.response.AuthResponse;
import com.sweatTV.dto.response.MessageResponse;
import com.sweatTV.entity.User;
import com.sweatTV.entity.enums.Role;
import com.sweatTV.mapper.UserMapper;
import com.sweatTV.repository.UserRepository;
import com.sweatTV.service.AuthService;
import com.sweatTV.service.EmailService;
import com.sweatTV.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final EmailService emailService;

    @Override
    public MessageResponse registerUser(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        String tempPassword = request.getPassword();

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Role.ROLE_USER);

        User savedUser = userRepository.save(user);
        emailService.sendWelcomeEmail(user.getEmail(), user.getUsername(), tempPassword);
        return new MessageResponse("User registered successfully, check your email for the next options");
    }

    @Override
    public AuthResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }

        String accessToken = jwtUtils.generateToken(user.getEmail(), user.getRoles());
        user.setVerificationToken(accessToken);
        user.setVerificationTokenExpiry(Instant.now().plusSeconds(86400));

        User savedUser = userRepository.save(user);
        return new AuthResponse(accessToken);
    }
}
