package com.seproject.healthqa.web.payload;

import com.seproject.healthqa.web.bean.Person;
import java.util.List;

public class AdminUserResponse {

    List<Person> admin;
    List<Person> doctor;

    public List<Person> getAdmin() {
        return admin;
    }

    public void setAdmin(List<Person> admin) {
        this.admin = admin;
    }

    public List<Person> getDoctor() {
        return doctor;
    }

    public void setDoctor(List<Person> doctor) {
        this.doctor = doctor;
    }

}
