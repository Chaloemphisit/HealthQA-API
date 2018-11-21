package com.seproject.healthqa.web.controller.admin;

import com.seproject.healthqa.domain.entity.Users;
import com.seproject.healthqa.exception.CustomException;
import com.seproject.healthqa.security.CurrentUser;
import com.seproject.healthqa.security.UserPrincipal;
import com.seproject.healthqa.service.AdminService;
import com.seproject.healthqa.web.payload.ApiResponse;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {

    private static Logger log = Logger.getLogger("InfoLogging");

    @Autowired
    AdminService adminService;

    @GetMapping(value = "/topic")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getReportTopic(@CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok().body(adminService.getTopic(currentUser));
    }

    @GetMapping(value = "/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(adminService.getUsers());
    }

    @PutMapping("/users/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteTopic(@PathVariable("id") String username) {
        log.info("\n\n\n\n\n------------------------>" + username);
        Optional<Users> user = adminService.deleteUser(username);
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomException(new Timestamp(System.currentTimeMillis()), 404, "Not Found", "User Not Found"));
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "ลบสำเร็จ"));
    }

}
