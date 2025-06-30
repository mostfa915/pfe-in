package com.example.pfev2.services;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsService {

    @Value("${twilio.phone_number}")
    private String fromNumber;

    public void sendSms(String to, String messageBody) {
        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(fromNumber),
                messageBody
        ).create();

        System.out.println("SMS envoyé avec SID : " + message.getSid());
    }
}
