package com.example.springbootpostgres.controller;

import com.example.springbootpostgres.model.MessageRequest;
import com.example.springbootpostgres.service.SmsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SmsController {

    @Value("${ACCOUNT_SID}")
    private String sid;

    @Value("${AUTH_TOKEN}")
    private String auth_token;

    @Autowired
    private SmsService smsService;

    @PostMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS(@Valid @RequestBody MessageRequest msgRequest) {
        return smsService.sendSMS(sid,auth_token,msgRequest);
    }
}
