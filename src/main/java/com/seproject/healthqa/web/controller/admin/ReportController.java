package com.seproject.healthqa.web.controller.admin;

import com.seproject.healthqa.domain.entity.Comment;
import com.seproject.healthqa.domain.entity.HeadTopic;
import com.seproject.healthqa.exception.CustomException;
import com.seproject.healthqa.service.CommentService;
import com.seproject.healthqa.service.ReportService;
import com.seproject.healthqa.service.TopicService;
import com.seproject.healthqa.web.bean.ReportCommentResponse;
import com.seproject.healthqa.web.bean.ReportTopicResponse;
import com.seproject.healthqa.web.payload.ApiResponse;
import com.seproject.healthqa.web.payload.ReportResponse;
import java.sql.Timestamp;
import java.util.List;
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
@RequestMapping("admin/report")
public class ReportController {

    private static Logger log = Logger.getLogger("InfoLogging");

    @Autowired
    private ReportService reportService;

    @Autowired
    CommentService commentService;

    @Autowired
    TopicService topicService;

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

    @PutMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Integer id) {
//        return ResponseEntity.ok().body(topicService.reportTopic(id));
        Optional<Comment> topic = commentService.deleteComment(id);
        if (!topic.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomException(new Timestamp(System.currentTimeMillis()), 404, "Not Found", "Comment Not Found"));
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "ลบสำเร็จ"));
    }

    @PutMapping("/topic/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable("id") Integer id) {
//        return ResponseEntity.ok().body(topicService.reportTopic(id));
        Optional<HeadTopic> topic = topicService.deleteTopic(id);
        if (!topic.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomException(new Timestamp(System.currentTimeMillis()), 404, "Not Found", "Topic Not Found"));
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "ลบสำเร็จ"));
    }

    @PutMapping("/comment/cancel/{id}")
    public ResponseEntity<?> cancelReportComment(@PathVariable("id") Integer id) {
//        return ResponseEntity.ok().body(topicService.reportTopic(id));
        Optional<Comment> topic = commentService.cancelReportComment(id);
        if (!topic.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomException(new Timestamp(System.currentTimeMillis()), 404, "Not Found", "Comment Not Found"));
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "ยกเลิกสำเร็จ"));
    }

    @PutMapping("/topic/cancel/{id}")
    public ResponseEntity<?> cancelReportTopic(@PathVariable("id") Integer id) {
//        return ResponseEntity.ok().body(topicService.reportTopic(id));
        Optional<HeadTopic> topic = topicService.cancelReportTopic(id);
        if (!topic.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomException(new Timestamp(System.currentTimeMillis()), 404, "Not Found", "Topic Not Found"));
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "ยกเลิกสำเร็จ"));
    }
}
