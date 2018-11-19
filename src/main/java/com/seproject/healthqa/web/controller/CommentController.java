package com.seproject.healthqa.web.controller;

import com.seproject.healthqa.domain.entity.Comment;
import com.seproject.healthqa.domain.entity.HeadTopic;
import com.seproject.healthqa.exception.CustomException;
import com.seproject.healthqa.security.CurrentUser;
import com.seproject.healthqa.security.UserPrincipal;
import com.seproject.healthqa.service.CommentService;
import com.seproject.healthqa.web.bean.NewComment;
import com.seproject.healthqa.web.payload.ApiResponse;
import java.sql.Timestamp;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping()
//    @PreAuthorize("hasAuthority('USER','S_USER')")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('S_USER')")
    public ResponseEntity<?> postTopic(@CurrentUser UserPrincipal currentUser,
            @RequestParam(value = "id", defaultValue = "-10") int topicId,
            @Valid @RequestBody NewComment body) {

        if (topicId == -10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Topic ID Not Found!"));
        }

        Comment commment = commentService.createComment(body, currentUser, topicId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commment);
    }

    @PutMapping("/report/{id}")
    public ResponseEntity<?> reportTopic(@PathVariable("id") Integer id) {
//        return ResponseEntity.ok().body(topicService.reportTopic(id));
        Optional<Comment> topic = commentService.reportComment(id);
        if (!topic.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomException(new Timestamp(System.currentTimeMillis()), 404, "Not Found", "Comment Not Found"));
        }

        return ResponseEntity.ok().body(new ApiResponse(true, "รายงานเสำเร็จ"));
    }
}
