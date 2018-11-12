package com.seproject.healthqa.web.controller;

import com.seproject.healthqa.domain.repository.UserRepository;
import com.seproject.healthqa.service.HomeService;
import com.seproject.healthqa.service.TopicService;
import com.seproject.healthqa.web.payload.UserIdentityAvailability;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {

    private static Logger log = Logger.getLogger("InfoLogging");

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String getHello(){
        return "Hello";
    }
    
    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        System.out.println(userRepository.existsByUsername(username));
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }
}
