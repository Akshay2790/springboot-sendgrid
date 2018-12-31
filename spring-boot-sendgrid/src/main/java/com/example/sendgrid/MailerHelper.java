package com.example.sendgrid;

import java.io.IOException;
import com.sendgrid.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MailerHelper {
  private static final Logger logger = LoggerFactory.getLogger(MailerHelper.class);

  public void sendMail(String address, String subject, String message) {
    Email from = new Email("akshaydikhar@gmail.com");
    Email to = new Email(address);
    Content emailContent = new Content("text/plain", message);

    SendGrid sendgrid = new SendGrid("YOUR_SENDGRID_KEY");
    Request request = new Request();

    Mail mail = new Mail(from, subject, to, emailContent);

    try {
      request.method = Method.POST;
      request.endpoint = "mail/send";
      request.body = mail.build();
      Response response = sendgrid.api(request);
      logger.info("--> Status: " + response.statusCode);
      logger.info("--> Body: " + response.body);
      logger.info("--> Header: " + response.headers);
    } catch(IOException ex) {
      logger.error(ex.getMessage());
    }
  }
}
