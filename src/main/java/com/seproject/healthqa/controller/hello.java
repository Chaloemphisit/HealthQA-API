package com.seproject.healthqa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {
    
    @GetMapping(path = "/")
    public String hello(){
        return "Hello World";
    }
}
