package com.example.springbootpostgres.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class SmsController {

    @GetMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS(@RequestBody ObjectNode JSONObject) {

        String to = JSONObject.get("to").asText().toString();
        String message = JSONObject.get("message").asText().toString();

        System.out.println(System.getenv("ACCOUNT_SID"));
        Twilio.init(System.getenv("ACCOUNT_SID"), System.getenv("AUTH_TOKEN") );

        Message.creator(new PhoneNumber(to),
                new PhoneNumber("+19382019822"), message).create();

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}
