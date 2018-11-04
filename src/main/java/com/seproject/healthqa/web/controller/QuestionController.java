package com.seproject.healthqa.web.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seproject.healthqa.service.HomeService;
import com.seproject.healthqa.service.QuestionService;
import com.seproject.healthqa.web.bean.AddQuestion;
import com.seproject.healthqa.web.bean.Topic;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
@RequestMapping("question")
public class QuestionController {

    private static Logger log = Logger.getLogger("InfoLogging");

    @Autowired
    QuestionService questionService;

    
    @GetMapping(value = "/topic/{id}")
//  @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public List<Topic> getTopic(@PathVariable("id") int id_topic) {
    	log.info(" ID_TOPIC ---------> "+id_topic);
        return questionService.getTopic(id_topic);
    }
    
//    @PostMapping(value = "/AddQt")
//    @ResponseBody
////  @PreAuthorize("hasRole('USER')")
//    public boolean addQuestion(@RequestBody AddQuestion addQt){
//      log.info("connected --------------------------------");
//      return true;
//    } 
    
    
    
}