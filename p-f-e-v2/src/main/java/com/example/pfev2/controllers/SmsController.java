package com.example.pfev2.controllers;

import com.example.pfev2.services.TwilioSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private TwilioSmsService smsService;

    @PostMapping("/send")
    public String sendSms(@RequestBody SmsRequest request) {
        smsService.sendSms(request.getTo(), request.getMessage());
        return "Message envoyé à " + request.getTo();
    }
}
class SmsRequest {
    private String to;
    private String message;

    // Getters et setters
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

