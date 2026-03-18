package com.sweatTV.exception;

import com.sweatTV.dto.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageResponse> handleUserNotFound(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResponse("User not found"));
    }
    @ExceptionHandler(EmailChangedException.class)
    public ResponseEntity<MessageResponse> handleEmailException(EmailChangedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new MessageResponse("Email updated. Please login again with new email."));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MessageResponse> handleCredentialsException(BadCredentialsException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new MessageResponse("Email is not verified. Please verify your email before logging in."));
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<MessageResponse> handleTokenException(InvalidTokenException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResponse("Verification link has expired. Please request a new one"));
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<MessageResponse> handleCredentialsException(InvalidCredentialsException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResponse("User not found with email: \" + email"));
    }
}
