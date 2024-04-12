package com.eventplanner.reservationms.payload.emailsenderconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {


    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    public void sendEmail(EmailsDetails emailDetails){
        try {
            SimpleMailMessage mailMsg = new SimpleMailMessage();
            mailMsg.setFrom(emailSender);
            mailMsg.setTo(emailDetails.getRecipient());
            mailMsg.setText(emailDetails.getMessageBody());
            mailMsg.setSubject(emailDetails.getSubject());
            javaMailSender.send(mailMsg);
            System.out.println("Mail sent successfully");
            //log.info("Mail sent successfully");
        }catch (MailException exception){
            System.out.println("Failure occurred while sending email");
            //log.debug("Failure occurred while sending email");
        }
    }
}