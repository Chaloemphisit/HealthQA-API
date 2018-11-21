package com.seproject.healthqa.web.controller.admin;

import com.seproject.healthqa.security.CurrentUser;
import com.seproject.healthqa.security.UserPrincipal;
import com.seproject.healthqa.service.AdminService;
import com.seproject.healthqa.service.HomeService;
import com.seproject.healthqa.service.TopicService;
import com.seproject.healthqa.utility.AppConstants;
import com.seproject.healthqa.web.bean.AllTopics;
import com.seproject.healthqa.web.bean.ReportTopicResponse;
import com.seproject.healthqa.web.controller.TopicController;
import com.seproject.healthqa.web.payload.PagedResponse;
import java.util.List;
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
public class AdminController {

    private static Logger log = Logger.getLogger("InfoLogging");

    @Autowired
    AdminService adminService;

    
    @GetMapping(value = "/topic")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ReportTopicResponse> getReportTopic(@CurrentUser UserPrincipal currentUser) {
        return adminService.getTopic(currentUser);
    }


}
