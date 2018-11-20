package com.seproject.healthqa.web.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EditProfileRequest {

    private String firstname;
    private String lastname;
    private String email;
//    @JsonIgnore(true)
    private String oldPassword;
    private String newPassword;
    
    

    public EditProfileRequest(String firstname, String lastname, String email, String oldPassword, String newPassword) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
