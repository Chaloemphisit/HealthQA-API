package com.seproject.healthqa.web.controller;

import com.seproject.healthqa.domain.RoleName;
import com.seproject.healthqa.domain.entity.Authority;
import com.seproject.healthqa.domain.entity.Users;
import com.seproject.healthqa.domain.repository.AuthorityRepository;
import com.seproject.healthqa.domain.repository.UserRepository;
import com.seproject.healthqa.exception.AppException;
import com.seproject.healthqa.security.JwtTokenProvider;
//import com.seproject.healthqa.security.service.JwtAuthenticationResponse;
import com.seproject.healthqa.web.payload.ApiResponse;
import com.seproject.healthqa.web.payload.JwtAuthenticationResponse;
import com.seproject.healthqa.web.payload.LoginRequest;
import com.seproject.healthqa.web.payload.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    AuthorityRepository authorityRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    JwtTokenProvider tokenProvider;
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Users user = new Users(signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getUsername(),
                signUpRequest.getPassword(), signUpRequest.getEmail());
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        Authority userRole = authorityRepository.findByName(RoleName.USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        
        user.setIsDeleted(false);
        user.setAuthority(Collections.singleton(userRole));
        
        Users result = userRepository.save(user);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
        
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
