package com.example.sendgrid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
  private static final Logger logger = LoggerFactory.getLogger(MailController.class);

  @Autowired
  private MailService mailerService;

  @Autowired
  private MailerHelper mailerHelper;

  @RequestMapping("/send-mail")
  public String sendMail(@RequestParam(value="address") String address,
                         @RequestParam(value="subject") String subject,
                         @RequestParam(value="message") String message) {
	  System.out.println("********************"+address);
	  System.out.println("********************"+subject);
	  System.out.println("********************"+message);
    Long start = System.currentTimeMillis();
    logger.info("starting controller");
    mailerHelper.sendMail(address, subject, message);
    return String.format("Message sent to in ms", address, System.currentTimeMillis() - start);
  }

  @RequestMapping("/send-mail-async")
  public String sendMailAsync(@RequestParam(value="address") String address,
                         @RequestParam(value="subject") String subject,
                         @RequestParam(value="message") String message) {
    Long start = System.currentTimeMillis();
    logger.info("starting controller");
    mailerService.sendMail(address, subject, message);
    return String.format("Message sent to in ms", address, System.currentTimeMillis() - start);
  }
}
