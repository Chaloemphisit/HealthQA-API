package com.seproject.healthqa.web.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.seproject.healthqa.service.HomeService;
import com.seproject.healthqa.web.bean.AllTopics;

@RestController
@RequestMapping("home")
public class HomeController {
	
	private static Logger log = Logger.getLogger("InfoLogging");
	
	@Autowired
	HomeService homeService;
    
    @GetMapping(value = "/getTopics")
//  @PreAuthorize("hasRole('USER')")
    public List<AllTopics> getTopics() {
        return homeService.getTopics();
    }
    
    @GetMapping(value = "/getTopicsAns")
//  @PreAuthorize("hasRole('USER')")
    public List<AllTopics> getTopicsAns() {
        return homeService.getTopicsAns();
    }
    
    @GetMapping(value = "/getTopicsNoAns")
//  @PreAuthorize("hasRole('USER')")
    public List<AllTopics> getTopicsNoAns() {
        return homeService.getTopicsNoAns();
    }
    

}
