package com.seproject.healthqa.web.payload;

public class PasswordIdentity {

    private boolean verified;

    public PasswordIdentity(boolean verified) {
        this.verified = verified;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}
