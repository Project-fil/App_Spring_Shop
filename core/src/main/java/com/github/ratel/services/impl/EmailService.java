package com.github.ratel.services.impl;

public interface EmailService {

   public String sendMessageToEmail(final String toAddress, final String subject, final String text);


}
