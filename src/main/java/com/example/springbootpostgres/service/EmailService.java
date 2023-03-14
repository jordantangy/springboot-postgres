package com.example.springbootpostgres.service;

import com.example.springbootpostgres.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getRecipient());
        message.setSubject(email.getSubject());
        message.setText(email.getMsgBody());
        mailSender.send(message);
    }
}
