package com.seproject.healthqa.web.controller;

import com.seproject.healthqa.domain.entity.HeadTopic;
import com.seproject.healthqa.security.CurrentUser;
import com.seproject.healthqa.security.UserPrincipal;
import com.seproject.healthqa.utility.EmailService;
import com.seproject.healthqa.utility.Mail;
import com.seproject.healthqa.web.payload.ApiResponse;
import com.seproject.healthqa.web.payload.EmailRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("email")
public class MailController {

    @Autowired
    private EmailService emailService;

    @PostMapping()
    public ResponseEntity<?> sendEmail(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody EmailRequest body) {
        Mail mail = new Mail();
        mail.setFrom(body.getEmail());
        mail.setTo("healthquestionanswer@gmail.com");
        mail.setSubject("Contact us: " + body.getSubject());
        mail.setContent("Subject : " + body.getSubject() + "\n"
                + "From : " + body.getEmail() + "\n"
                + "Detail : " + body.getContent());

        emailService.sendSimpleMessage(mail);
        return ResponseEntity.ok().body(new ApiResponse(true, "sent contact us successful"));
    }
}
