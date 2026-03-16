package com.sweatTV.service;

import com.sweatTV.dto.request.LoginRequest;
import com.sweatTV.dto.request.RegisterUserRequest;
import com.sweatTV.dto.response.AuthResponse;
import com.sweatTV.dto.response.MessageResponse;
import org.jspecify.annotations.Nullable;

public interface AuthService {

    MessageResponse registerUser(RegisterUserRequest request);

    AuthResponse loginUser(LoginRequest request);

    MessageResponse verifyEmail(String token);
}
