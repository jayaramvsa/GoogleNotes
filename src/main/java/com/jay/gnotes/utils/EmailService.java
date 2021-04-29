package com.jay.gnotes.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendEmail(String to, String subject, String context) {
        try {
            SimpleMailMessage smp = new SimpleMailMessage();
            smp.setSubject(subject);
            smp.setText(context);
            smp.setTo(to);
            smp.setFrom("jayaramvsa@gmail.com");
            javaMailSender.send(smp);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
