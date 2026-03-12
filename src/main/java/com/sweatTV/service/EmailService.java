package com.sweatTV.service;

public interface EmailService {

    void sendCredentials(String toEmail, String username, String password);

    void sendWelcomeEmail(String toEmail, String username, String password);
}
