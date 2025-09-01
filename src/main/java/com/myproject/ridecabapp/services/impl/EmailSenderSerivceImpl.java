package com.myproject.ridecabapp.services.impl;

import com.myproject.ridecabapp.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderSerivceImpl implements EmailSenderService {

    private final JavaMailSender javaMailSender;  // used to send emails
    @Override
    public void sendEmail(String toEmail, String subject, String body) {

        try {
            //create email message
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(toEmail);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);
            log.info("Email sent successfully");
        }
        catch (Exception e){
         log.info("Cannot send Email"+ e.getMessage());
        }
    }
    @Override
    public void sendEmail(String[] toEmail, String subject, String body) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setTo("aakkashsingh07@gmail.com");
            simpleMailMessage.setBcc(toEmail);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);
            log.info("Email sent successfully");
        } catch (Exception e) {
            log.info("Cannot send email, "+e.getMessage());
        }
    }
}
