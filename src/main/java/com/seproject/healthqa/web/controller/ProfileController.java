package com.seproject.healthqa.web.controller;

import com.seproject.healthqa.domain.repository.UserRepository;
import com.seproject.healthqa.security.CurrentUser;
import com.seproject.healthqa.security.UserPrincipal;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seproject.healthqa.service.ProfileService;
import com.seproject.healthqa.service.TopicService;
import com.seproject.healthqa.web.bean.AllTopics;
import com.seproject.healthqa.web.payload.EditProfileRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@RestController
@RequestMapping("profile")
public class ProfileController {

    private static Logger log = Logger.getLogger("InfoLogging");

    @Autowired
    ProfileService profileService;

    @Autowired
    TopicService topicService;
    
    @Autowired
    UserRepository userRepository;
    
    @GetMapping(value = "/{username}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('S_USER')")
    public ResponseEntity<?> getUser(@CurrentUser UserPrincipal currentUser, @PathVariable("username") String username) {
        return ResponseEntity.ok(profileService.getProfile(username));
    }

    @GetMapping(value = "/topic")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('S_USER')")
    public List<AllTopics> getUserTopic(@CurrentUser UserPrincipal currentUser) {
        return topicService.getUserTopic(currentUser);
    }

    @GetMapping(value = "/comment")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('S_USER')")
    public List<AllTopics> getUserHasComment(@CurrentUser UserPrincipal currentUser) {
        return topicService.getUserHasComment(currentUser);
    }

    @PostMapping(value = "/edit")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('S_USER')")
    @ResponseBody
    public ResponseEntity<?> getTopic(@CurrentUser UserPrincipal currentUser, EditProfileRequest body) {
        return profileService.editProfile(currentUser, body);
    }
}
