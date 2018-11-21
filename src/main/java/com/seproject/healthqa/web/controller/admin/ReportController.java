package com.seproject.healthqa.web.controller.admin;

import com.seproject.healthqa.security.CurrentUser;
import com.seproject.healthqa.security.UserPrincipal;
import com.seproject.healthqa.service.ReportService;
import com.seproject.healthqa.service.TopicService;
import com.seproject.healthqa.web.bean.AllTopics;
import com.seproject.healthqa.web.bean.ReportCommentResponse;
import com.seproject.healthqa.web.bean.ReportTopicResponse;
import com.seproject.healthqa.web.controller.TopicController;
import com.seproject.healthqa.web.payload.ReportResponse;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("admin/report")
public class ReportController {

    private static Logger log = Logger.getLogger("InfoLogging");

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/topic")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ReportTopicResponse> getReportTopic() {
        return reportService.getReportTopic();
    }

    @GetMapping(value = "/comment")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ReportCommentResponse> getReportComment() {
        return reportService.getReportComment();
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ReportResponse getReports() {
        return reportService.getReport();
    }

}
