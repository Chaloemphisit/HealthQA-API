package com.seproject.healthqa.web.controller;

import com.seproject.healthqa.domain.entity.HeadTopic;
import com.seproject.healthqa.exception.CustomException;
import com.seproject.healthqa.security.CurrentUser;
import com.seproject.healthqa.security.UserPrincipal;
import com.seproject.healthqa.service.HomeService;
import com.seproject.healthqa.service.TopicService;
import com.seproject.healthqa.web.bean.AllTopics;
import com.seproject.healthqa.web.bean.Topic;
import com.seproject.healthqa.web.payload.ApiResponse;
import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javassist.tools.web.BadHttpRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        return topicService.getTopic(id_topic);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> postTopic(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody HeadTopic body) {
        HeadTopic headTopic = topicService.createTopic(body, currentUser);
//        return ResponseEntity.status(HttpStatus.CREATED).body(headTopic);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/topic/{headtopicid}")
                .buildAndExpand(headTopic.getHeadTopicId()).toUri();

        String redirect = "/topic/" + headTopic.getHeadTopicId();

        return ResponseEntity.created(location).body(new ApiResponse(true, "ตั้งคำถามสำเร็จ", redirect));
    }

    @GetMapping(value = "/report/{id_topic}")
//  @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public boolean isReportTp(@PathVariable("id_topic") int id_topic) {
        log.info(" ID_TOPIC ---------> " + id_topic);
        return topicService.reportTp(id_topic);
    }

    @GetMapping(value = "/report/{id_topic}/{id_comment}")
//  @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public boolean isReportCm(@PathVariable("id_topic") int id_topic, @PathVariable("id_comment") int id_comment) {
        log.info(" ID_TOPIC ---------> " + id_topic);
        return false;
    }

    @PutMapping("/report/{id}")
    public ResponseEntity<?> reportTopic(@PathVariable("id") Integer id){
//        return ResponseEntity.ok().body(topicService.reportTopic(id));
        Optional<HeadTopic> topic = topicService.reportTopic(id);
        if (!topic.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomException(new Timestamp(System.currentTimeMillis()), 404, "Not Found", "Topic Not Found"));
        }
        
        return ResponseEntity.ok().body(new ApiResponse(true, "รายงานเสำเร็จ"));
    }
}
