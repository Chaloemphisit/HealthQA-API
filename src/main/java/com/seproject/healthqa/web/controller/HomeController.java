package com.seproject.healthqa.web.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.seproject.healthqa.service.HomeService;

@RestController
@RequestMapping("home")
public class HomeController {
	
	private static Logger log = Logger.getLogger("InfoLogging");
	
	@Autowired
	HomeService homeService;

    @GetMapping(value = "/Test")
//  @PreAuthorize("hasRole('USER')")
    public boolean getTest() {
    	log.info("CONECTED CONTROLLER");
    	String x = "CONECTED SERVICE";
        return homeService.Test(x);
    }
    
    @GetMapping(value = "/topic")
//  @PreAuthorize("hasRole('USER')")
    public list<> getTopics() {
    	log.info("CONECTED CONTROLLER");
        return homeService.getListTopics();
    }

}
