package com.example.springbootpostgres.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageRequest {

    @NotBlank(message = "The phone number should not be empty")
    private String phoneNumber;

    @NotBlank(message = "The message should not be empty")
    private String message;
    
}
