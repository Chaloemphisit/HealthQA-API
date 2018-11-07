package com.seproject.healthqa.web.controller;

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
import com.seproject.healthqa.web.bean.AllTopics;


@CrossOrigin
@RestController
@RequestMapping("profile")
public class ProfileController {

    private static Logger log = Logger.getLogger("InfoLogging");

    @Autowired
    ProfileService profileService;
    
  @GetMapping(value = "/{id}")
//@PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> getUser(@PathVariable("id") int id_user) {
      return ResponseEntity.ok(profileService.getProfile(id_user));
  }
    

   
    
    
    
}