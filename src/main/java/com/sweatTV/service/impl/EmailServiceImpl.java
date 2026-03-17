package com.sweatTV.service.impl;

import com.sweatTV.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender javaMailSender;
    private final JavaMailSenderImpl mailSender;

    @Value("${app.frontend.url:http://localhost:4200}")
    private String frontendUrl;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendVerification(String toEmail, String username, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject("Email Verification - SweatTV");

            String verificationLink = frontendUrl + "/verify-email?token=" + token;
            String emailBody = "Dear User,\n\n"
                    + "Thank you for registering on SweatTV! Please click the link below to verify your email address:\n"
                    + verificationLink + "\n\n"
                    + "If you did not sign up for this account, please ignore this email.\n\n"
                    + "Best regards,\n"
                    + "SweatTV  Team";

            message.setText(emailBody);
            mailSender.send(message);

            logger.info("Temporary password email sent to {}", toEmail);
        }catch (Exception ex){
            logger.error("Failed to send temporary password email {}: {}", toEmail, ex.getMessage(), ex);
            throw new RuntimeException("Failed to send temporary password email");
        }

    }

    @Override
    public void sendPasswordResetEmail(String toEmail, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject("Password Reset - SweatTv");

            String resetLink = frontendUrl + "/reset-password?token=" + token;
            String emailBody = "Dear User,\n\n"
                    + "We received a request to reset your password. Please click the link below to reset your password:\n"
                    + resetLink + "\n\n"
                    + "If you did not request a password reset, please ignore this email.\n\n"
                    + "Best regards,\n"
                    + "SweatTv Team";

            message.setText(emailBody);
            mailSender.send(message);
            logger.info("Password reset email sent to {}", toEmail);
        }catch (Exception ex){
            logger.error("Failed to send password reset email to {}: {}", toEmail, ex.getMessage(), ex);
            throw new RuntimeException("Failed to send password reset email. Please try again later");
        }

    }
}
