package com.example.springbootpostgres.repository;


import com.example.springbootpostgres.model.Email;

// Interface
public interface EmailRepository {

    // Method
    // To send a simple email
    String sendSimpleMail(Email details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(Email details);
}
