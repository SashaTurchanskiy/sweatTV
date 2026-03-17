package com.sweatTV.service;

public interface EmailService {

    void sendVerification(String toEmail, String username, String token);

    public void sendPasswordResetEmail(String toEmail, String token);
}
