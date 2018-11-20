package com.seproject.healthqa.web.controller.admin;

import com.seproject.healthqa.web.controller.*;
import com.seproject.healthqa.domain.repository.UserRepository;
import com.seproject.healthqa.security.CurrentUser;
import com.seproject.healthqa.security.UserPrincipal;
import com.seproject.healthqa.service.HomeService;
import com.seproject.healthqa.service.TopicService;
import com.seproject.healthqa.web.payload.UserIdentityAvailability;
import com.seproject.healthqa.web.payload.UserSummary;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminsController {

    private static Logger log = Logger.getLogger("InfoLogging");

    @Autowired
    UserRepository userRepository;

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getFirstname(), currentUser.getLastname(),
                currentUser.getUsername(), currentUser.getEmail(), currentUser.getAuthorities());
        return userSummary;
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
