package com.seproject.healthqa.service;

import com.seproject.healthqa.exception.BadRequestException;
import com.seproject.healthqa.security.UserPrincipal;
import com.seproject.healthqa.utility.AppConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import com.seproject.healthqa.web.bean.AllTopics;
import com.seproject.healthqa.web.payload.PagedResponse;
import java.util.Collections;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class HomeService {

    private static Logger log = Logger.getLogger("InfoLogging");

    @PersistenceContext
    EntityManager entityManager;

    public PagedResponse<AllTopics> getAllTopics(int page, int size) {
        StringBuffer queryStr = new StringBuffer("SELECT HD.HEAD_TOPIC_ID as ID ,HD.TOPIC_NAME,HD.TOPIC_TEXT,HD.QUESTION_TYPE "
                + " , (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=ID AND IS_DELETED='F') as commentCount "
                + " FROM head_topic HD WHERE HD.IS_DELETED = 'F' ORDER BY CREATED_DATE DESC");
//                + " LIMIT " + page + " OFFSET " + size);

        List<AllTopics> BeanList = new ArrayList<AllTopics>();

        Query query = entityManager.createNativeQuery(queryStr.toString());

        query.setFirstResult(page - 1);
        query.setMaxResults(size);

        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
            AllTopics Bean = new AllTopics();
            Bean.setTopicId(Integer.parseInt(obj[0].toString()));
            Bean.setTopicName(obj[1].toString());
            Bean.setTopicText(obj[2].toString());
//            Bean.setQuestion_type(obj[3].toString());
            if ((obj[3].toString()).equals("D")) {
                Bean.setQuestionType("คำถามเฉพาะทางแพทย์");
            } else {
                Bean.setQuestionType("คำถามเฉพาะทางเภสัชกร");
            }

            Bean.setAnswerCount(Integer.parseInt(obj[4].toString()));

            BeanList.add(Bean);
        }

        boolean last = false;
        if (objectList.size() < size) {
            last = true;
        } else {
            page += size;
        }
        return new PagedResponse<>(BeanList, page, size, last);
    }

    public PagedResponse<AllTopics> getTopicsAns(int page, int size) {
        StringBuffer queryStr = new StringBuffer(" SELECT HD.HEAD_TOPIC_ID as ID ,HD.TOPIC_NAME,HD.TOPIC_TEXT,HD.QUESTION_TYPE, "
                + " (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=HD.HEAD_TOPIC_ID AND IS_DELETED='F') as commenntCount "
                + " FROM head_topic HD "
                + " WHERE HD.IS_DELETED = 'F' AND (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=HD.HEAD_TOPIC_ID AND IS_DELETED='F') > 0 "
                + " ORDER BY CREATED_DATE DESC ");
        List<AllTopics> BeanList = new ArrayList<AllTopics>();

        Query query = entityManager.createNativeQuery(queryStr.toString());
        query.setFirstResult(page - 1);
        query.setMaxResults(size);

        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
            AllTopics Bean = new AllTopics();
            Bean.setTopicId(Integer.parseInt(obj[0].toString()));
            Bean.setTopicName(obj[1].toString());
            Bean.setTopicText(obj[2].toString());
//            Bean.setQuestion_type(obj[3].toString());
            if ((obj[3].toString()).equals("D")) {
                Bean.setQuestionType("คำถามเฉพาะทางแพทย์");
            } else {
                Bean.setQuestionType("คำถามเฉพาะทางเภสัชกร");
            }

            Bean.setAnswerCount(Integer.parseInt(obj[4].toString()));

            BeanList.add(Bean);
        }
        boolean last = false;
        if (objectList.size() < size) {
            last = true;
        } else {
            page += size;
        }
        return new PagedResponse<>(BeanList, page, size, last);
    }

    public PagedResponse<AllTopics> getTopicsNoAns(int page, int size) {
        StringBuffer queryStr = new StringBuffer(" SELECT HD.HEAD_TOPIC_ID as ID ,HD.TOPIC_NAME,HD.TOPIC_TEXT,HD.QUESTION_TYPE, "
                + " (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=HD.HEAD_TOPIC_ID AND IS_DELETED='F') as commenntCount "
                + " FROM head_topic HD "
                + " WHERE HD.IS_DELETED = 'F' AND (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=HD.HEAD_TOPIC_ID AND IS_DELETED='F') = 0 "
                + " ORDER BY CREATED_DATE DESC ");
        List<AllTopics> BeanList = new ArrayList<AllTopics>();

        Query query = entityManager.createNativeQuery(queryStr.toString());
        query.setFirstResult(page - 1);
        query.setMaxResults(size);

        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
            AllTopics Bean = new AllTopics();
            Bean.setTopicId(Integer.parseInt(obj[0].toString()));
            Bean.setTopicName(obj[1].toString());
            Bean.setTopicText(obj[2].toString());
//            Bean.setQuestion_type(obj[3].toString());
            if ((obj[3].toString()).equals("D")) {
                Bean.setQuestionType("คำถามเฉพาะทางแพทย์");
            } else {
                Bean.setQuestionType("คำถามเฉพาะทางเภสัชกร");
            }

            Bean.setAnswerCount(Integer.parseInt(obj[4].toString()));

            BeanList.add(Bean);
        }
        boolean last = false;
        if (objectList.size() < size) {
            last = true;
        } else {
            page += size;
        }
        return new PagedResponse<>(BeanList, page, size, last);
    }

    private void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

    public List<AllTopics> getSearchResult(String q) {
        StringBuffer queryStr = new StringBuffer("SELECT HEAD_TOPIC_ID , TOPIC_NAME, TOPIC_TEXT, QUESTION_TYPE, "
                + " (SELECT COUNT(*) FROM comment WHERE comment.HEAD_TOPIC_ID = head_topic.HEAD_TOPIC_ID AND IS_DELETED='F') as commenntCount "
                + " FROM head_topic "
                + " WHERE head_topic.IS_DELETED = 'F' AND (head_topic.TOPIC_TEXT LIKE '%" + q + "%' OR head_topic.TOPIC_NAME LIKE '%" + q + "%')");

        List<AllTopics> BeanList = new ArrayList<AllTopics>();

        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
            AllTopics Bean = new AllTopics();
            Bean.setTopicId(Integer.parseInt(obj[0].toString()));
            Bean.setTopicName(obj[1].toString());
            Bean.setTopicText(obj[2].toString());
//            Bean.setQuestion_type(obj[3].toString());
            if ((obj[3].toString()).equals("D")) {
                Bean.setQuestionType("คำถามเฉพาะทางแพทย์");
            } else {
                Bean.setQuestionType("คำถามเฉพาะทางเภสัชกร");
            }

            Bean.setAnswerCount(Integer.parseInt(obj[4].toString()));

            BeanList.add(Bean);
        }
        return BeanList;
    }

}
