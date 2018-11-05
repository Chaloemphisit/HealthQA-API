package com.seproject.healthqa.web.controller;

import com.seproject.healthqa.domain.entity.HeadTopic;
import com.seproject.healthqa.service.HomeService;
import com.seproject.healthqa.service.TopicService;
import com.seproject.healthqa.web.bean.AllTopics;
import com.seproject.healthqa.web.bean.Topic;
import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("topic")
public class TopicController {

    private static Logger log = Logger.getLogger("InfoLogging");

    @Autowired
    HomeService homeService;
    @Autowired
    TopicService topicService;

    @GetMapping(value = "/all")
//  @PreAuthorize("hasRole('USER')")
    public List<AllTopics> getTopics() {
        return homeService.getTopics();
    }

    @GetMapping(value = "/ans")
//  @PreAuthorize("hasRole('USER')")
    public List<AllTopics> getTopicsAns() {
        return homeService.getTopicsAns();
    }

    @GetMapping(value = "/noAns")
//  @PreAuthorize("hasRole('USER')")
    public List<AllTopics> getTopicsNoAns() {
        return homeService.getTopicsNoAns();
    }
    
    @GetMapping(value = "/{id}")
//  @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public ResponseEntity<?> getTopic(@PathVariable("id") int id_topic) {
    	log.info(" ID_TOPIC ---------> "+id_topic);
        return ResponseEntity.ok(topicService.getTopic(id_topic));
    }
    
    @PostMapping()
    public ResponseEntity<?> postTopic(@Valid @RequestBody HeadTopic body) {
        HeadTopic headTopic = topicService.createTopic(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(headTopic);
    }
}
