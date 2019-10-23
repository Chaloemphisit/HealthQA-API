package com.seproject.healthqa.web.controller;

import com.seproject.healthqa.domain.entity.HeadTopic;
import com.seproject.healthqa.exception.CustomException;
import com.seproject.healthqa.security.CurrentUser;
import com.seproject.healthqa.security.UserPrincipal;
import com.seproject.healthqa.service.HomeService;
import com.seproject.healthqa.service.TopicService;
import com.seproject.healthqa.utility.AppConstants;
import com.seproject.healthqa.web.bean.AllTopics;
import com.seproject.healthqa.web.bean.Topic;
import com.seproject.healthqa.web.payload.ApiResponse;
import com.seproject.healthqa.web.payload.PagedResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    public PagedResponse<AllTopics> getTopics(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return homeService.getAllTopics(page, size);
    }
    
    @GetMapping(value = "/ans")
    public PagedResponse<AllTopics> getTopicsAns(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return homeService.getTopicsAns(page, size);
    }
    
    @GetMapping(value = "/noAns")
    public PagedResponse<AllTopics> getTopicsNoAns(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return homeService.getTopicsNoAns(page, size);
    }
    
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> getTopic(@PathVariable("id") int id_topic) {
        return topicService.getTopic(id_topic);
    }
    
    @PostMapping()
    public ResponseEntity<?> postTopic(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody HeadTopic body) {
        HeadTopic headTopic = topicService.createTopic(body, currentUser);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/topic/{headtopicid}")
                .buildAndExpand(headTopic.getHeadTopicId()).toUri();
        
        String redirect = "/topic/" + headTopic.getHeadTopicId();
        
        return ResponseEntity.created(location).body(new ApiResponse(true, "ตั้งคำถามสำเร็จ", redirect));
    }
    
    @PutMapping("/report/{id}")
    public ResponseEntity<?> reportTopic(@PathVariable("id") Integer id) {
        Optional<HeadTopic> topic = topicService.reportTopic(id);
        if (!topic.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomException(new Timestamp(System.currentTimeMillis()), 404, "Not Found", "Topic Not Found"));
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "รายงานสำเร็จ"));
    }
    
    @GetMapping(value = "/search")
    public List<AllTopics> getSearchResult(@RequestParam(value = "q", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) String q,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return homeService.getSearchResult(q);
    }
}
