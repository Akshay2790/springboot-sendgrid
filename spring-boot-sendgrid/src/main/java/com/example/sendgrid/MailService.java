package com.example.sendgrid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MailService {
  private static final Logger logger = LoggerFactory.getLogger(MailService.class);

  @Autowired
  private MailerHelper mailerHelper;

  @Async
  public void sendMail(String address, String subject, String message) {
    mailerHelper.sendMail(address, subject, message);
  }
}
