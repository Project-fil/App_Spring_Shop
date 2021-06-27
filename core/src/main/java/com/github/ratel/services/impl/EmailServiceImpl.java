package com.github.ratel.services.impl;

import com.github.ratel.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender mailSender;

    public String sendMessageToEmail(String toAddress, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        mailSender.send(simpleMailMessage);
    return "Email Send";
    }
}
