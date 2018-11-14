package com.seproject.healthqa.service;

import com.seproject.healthqa.domain.entity.Comment;
import com.seproject.healthqa.domain.entity.HeadTopic;
import com.seproject.healthqa.domain.entity.Users;
import com.seproject.healthqa.domain.repository.CommentRepository;
import com.seproject.healthqa.security.UserPrincipal;
import com.seproject.healthqa.web.bean.NewComment;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    CommentRepository commentRepository;

    public Comment createComment(NewComment body, UserPrincipal currentUser, int topicId) {
        Users userId = new Users();
        userId.setId(currentUser.getId());

        HeadTopic headTopicId = new HeadTopic();
        headTopicId.setHeadTopicId(topicId);

        Comment comment = new Comment();
        comment.setCommentText(body.getCommentText());
        comment.setHeadTopicId(headTopicId);
        comment.setUserId(userId);
        comment.setIsDeleted('F');
        comment.setReportStatus('F');

        return commentRepository.save(comment);
    }
}
