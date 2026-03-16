package com.sweatTV.service;

public interface EmailService {

    void sendVerification(String toEmail, String username, String token);

    void sendWelcomeEmail(String toEmail, String username, String password);
}
