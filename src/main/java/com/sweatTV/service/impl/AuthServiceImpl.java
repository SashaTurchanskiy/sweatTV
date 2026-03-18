package com.sweatTV.service.impl;

import com.sweatTV.dto.request.LoginRequest;
import com.sweatTV.dto.request.RegisterUserRequest;
import com.sweatTV.dto.response.AuthResponse;
import com.sweatTV.dto.response.MessageResponse;
import com.sweatTV.entity.User;
import com.sweatTV.entity.enums.Role;
import com.sweatTV.exception.BadCredentialsException;
import com.sweatTV.exception.InvalidCredentialsException;
import com.sweatTV.exception.InvalidTokenException;
import com.sweatTV.mapper.UserMapper;
import com.sweatTV.repository.UserRepository;
import com.sweatTV.service.AuthService;
import com.sweatTV.service.EmailService;
import com.sweatTV.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
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

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmailVerified(false);
        String verificationToken = UUID.randomUUID().toString();
        user.setVerificationToken(verificationToken);
        user.setVerificationTokenExpiry(Instant.now().plusSeconds(86400));
        user.setRoles(Role.ROLE_USER);

        log.info("Saving user: username={}, email={}, password={}",
                user.getUsername(), user.getEmail(), user.getPassword());


        User savedUser = userRepository.save(user);
        emailService.sendVerification(user.getEmail(), user.getUsername(), verificationToken);
        return new MessageResponse("User registered successfully, check your email for the next options");
    }

    @Override
    public AuthResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }
        if (!user.isEmailVerified()){
            throw new BadCredentialsException("Email is not verified. Please verify your email before logging in.");
        }

        String accessToken = jwtUtils.generateToken(user.getEmail(), user.getRoles());
        user.setVerificationToken(accessToken);
        user.setVerificationTokenExpiry(Instant.now().plusSeconds(86400));

        User savedUser = userRepository.save(user);
        return new AuthResponse(accessToken);
    }

    @Override
    public MessageResponse verifyEmail(String token) {
        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(()-> new InvalidTokenException("Invalid or expired verification token"));

        if (user.getVerificationTokenExpiry() == null || user.getVerificationTokenExpiry().isBefore(Instant.now())){
            throw new InvalidTokenException("Verification link has expired. Please request a new one");
        }

        user.setEmailVerified(true);
        user.setVerificationToken(null);
        user.setVerificationTokenExpiry(null);
        userRepository.save(user);
        return new MessageResponse("Email verified successfully! You can login now");
    }

    @Override
    public MessageResponse forgotPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Email not found"));

        String resetToken = UUID.randomUUID().toString();
        user.setPasswordResetToken(resetToken);
        user.setPasswordResetTokenExpiry(Instant.now().plusSeconds(3600));
        userRepository.save(user);
        emailService.sendPasswordResetEmail(email, resetToken);

        return new MessageResponse("Password reset email sent successfully! Please check your inbox");
    }

    @Override
    public MessageResponse resetPassword(String token, String newPassword) {
        User user = userRepository.findByPasswordResetToken(token)
                .orElseThrow(()-> new InvalidTokenException("Invalid or expired reset token"));

        if (user.getPasswordResetToken() == null || user.getPasswordResetTokenExpiry().isBefore(Instant.now())){
            throw new InvalidTokenException("Reset token has expire");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setPasswordResetToken(null);
        user.setPasswordResetTokenExpiry(null);

        userRepository.save(user);

        return new MessageResponse("Password successfully reset. You can login with your new password now");
    }

    @Override
    public MessageResponse changePassword(String email, String currentPassword, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Email is incorrect"));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())){
            throw new InvalidCredentialsException("Current password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return new MessageResponse("Password changed successfully");
    }

}
