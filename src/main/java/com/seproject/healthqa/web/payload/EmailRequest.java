package com.seproject.healthqa.web.payload;

import javax.validation.constraints.NotBlank;

public class EmailRequest {

    @NotBlank
    private String subject;

    @NotBlank
    private String email;

    @NotBlank
    private String content;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
