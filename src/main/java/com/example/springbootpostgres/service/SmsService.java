package com.example.springbootpostgres.service;

import com.example.springbootpostgres.model.MessageRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public ResponseEntity<String> sendSMS(String sid, String auth_token, MessageRequest msgRequest){

        Twilio.init(sid, auth_token);
        try{
            Message.creator(new PhoneNumber(msgRequest.getPhoneNumber()),
                    new PhoneNumber("+19382019822"), msgRequest.getMessage()).create();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}
