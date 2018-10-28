package com.seproject.healthqa.web.controller;

import java.text.ParseException;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seproject.healthqa.web.bean.AddQuestion;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	private static Logger log = Logger.getLogger("InfoLogging");
	
    @PostMapping(value = "/Topic")
//  @PreAuthorize("hasRole('USER')")
    public boolean addTimesheet(@RequestBody AddQuestion addQuestion) throws ParseException{
        
      log.info("--------------------------- connected controller-------------");
      return true;
    }  
}