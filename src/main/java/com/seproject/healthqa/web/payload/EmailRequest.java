package com.seproject.healthqa.web.payload;

import javax.validation.constraints.NotBlank;

public class EmailRequest {

    @NotBlank
    private String Subject;

    @NotBlank
    private String email;

    @NotBlank
    private String content;

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
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
