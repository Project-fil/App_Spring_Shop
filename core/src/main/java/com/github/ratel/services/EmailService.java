package com.github.ratel.services;

public interface EmailService {

   public String sendMessageToEmail(final String toAddress, final String subject, final String text);


}
