package com.seproject.healthqa.utility;

import com.seproject.healthqa.web.payload.EmailRequest;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(final Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setTo(mail.getTo());
        message.setFrom(mail.getFrom());

        emailSender.send(message);
    }

    public void sendEmailV2(EmailRequest body) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        // Enable the multipart flag!
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("healthquestionanswer@gmail.com");
        helper.setText("<html><body>"
                + "<div><h3>Subject : </h3>" + body.getSubject() + "</div>"
                + "<div><h3>From : </h3>" + body.getEmail()+ "</div>"
                + "<div><h3>Detail : </h3>" + body.getContent() + ""+ "</div>"
                + "<body></html>", true);
        helper.setSubject(body.getSubject());

        emailSender.send(message);
    }

}
