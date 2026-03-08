package com.dharnish.updatecheck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendChangeAlert(String email, String url) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Website Change Detected");
        message.setText(
                "Hello,\n\n" +
                "The website you are tracking has changed.\n\n" +
                "URL: " + url + "\n\n" +
                "Regards,\nUpdateCheck"
        );

        mailSender.send(message);
    }
}