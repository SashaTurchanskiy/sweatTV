package com.sweatTV.controller;

import com.sweatTV.dto.request.LoginRequest;
import com.sweatTV.dto.request.RegisterUserRequest;
import com.sweatTV.dto.response.AuthResponse;
import com.sweatTV.dto.response.MessageResponse;
import com.sweatTV.entity.User;
import com.sweatTV.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterUserRequest request){
        MessageResponse response = authService.registerUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        AuthResponse response = authService.loginUser(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/verify-email")
    public ResponseEntity<MessageResponse> verifyEmail(@RequestParam String token){
        return ResponseEntity.ok(authService.verifyEmail(token));
    }
}
