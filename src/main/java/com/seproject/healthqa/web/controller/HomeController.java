package com.seproject.healthqa.web.controller;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {
	
	private static Logger log = Logger.getLogger("InfoLogging");


    @GetMapping(value = "/Test")
//  @PreAuthorize("hasRole('USER')")
    public String getMonthYearTs() {
    	String x = "Connected";
    	log.info("TEST COMPLETED");
        return x;
    }

}
