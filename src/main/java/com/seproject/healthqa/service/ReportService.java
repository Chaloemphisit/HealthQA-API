package com.seproject.healthqa.service;

import com.seproject.healthqa.domain.repository.CommentRepository;
import com.seproject.healthqa.security.UserPrincipal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.seproject.healthqa.domain.repository.HeadTopicRepository;
import com.seproject.healthqa.web.bean.ReportCommentResponse;
import com.seproject.healthqa.web.bean.ReportTopicResponse;
import com.seproject.healthqa.web.payload.ReportResponse;

@Service
public class ReportService {

    private static Logger log = Logger.getLogger("InfoLogging");

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    HeadTopicRepository topicRepository;

    @Autowired
    CommentRepository commentRepository;

    public List<ReportTopicResponse> getReportTopic() {
        StringBuffer queryStr = new StringBuffer("SELECT HD.HEAD_TOPIC_ID as ID ,HD.TOPIC_NAME,HD.TOPIC_TEXT"
                + " FROM head_topic HD WHERE HD.IS_DELETED = 'F' AND HD.REPORT_STATUS = 'T'"
                + " ORDER BY CREATED_DATE DESC");

        List<ReportTopicResponse> BeanList = new ArrayList<ReportTopicResponse>();

        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
            ReportTopicResponse Bean = new ReportTopicResponse();
            Bean.setId(Long.parseLong(obj[0].toString()));
            Bean.setTopicName(obj[1].toString());
            Bean.setTopicText(obj[2].toString());
//            Bean.setQuestion_type(obj[3].toString());
            BeanList.add(Bean);
        }
        return BeanList;
    }

    public List<ReportCommentResponse> getReportComment() {
        StringBuffer queryStr = new StringBuffer("SELECT COMMENT_ID,HEAD_TOPIC_ID,COMMENT_TEXT "
                + " FROM comment "
                + " WHERE IS_DELETED='F' AND REPORT_STATUS='T'"
                + " ORDER BY CREATED_DATE DESC");

        List<ReportCommentResponse> BeanList = new ArrayList<ReportCommentResponse>();

        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
            ReportCommentResponse Bean = new ReportCommentResponse();
            Bean.setId(Long.parseLong(obj[0].toString()));
            Bean.setTopicId(Long.parseLong(obj[1].toString()));
            Bean.setCommentText(obj[2].toString());

            BeanList.add(Bean);
        }
        return BeanList;
    }

    public ReportResponse getReport() {
        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setTopic(getReportTopic());
        reportResponse.setComment(getReportComment());

        return reportResponse;
    }
}
