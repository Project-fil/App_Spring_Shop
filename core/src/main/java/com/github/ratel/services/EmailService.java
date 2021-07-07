package com.github.ratel.services;

public interface EmailService {

   void sendMessageToEmail(String toAddress, String subject, String text);


}
