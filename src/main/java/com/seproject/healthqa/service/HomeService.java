package com.seproject.healthqa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import com.seproject.healthqa.web.bean.AllTopics;

@Service
public class HomeService {

    private static Logger log = Logger.getLogger("InfoLogging");

    @PersistenceContext
    EntityManager entityManager;

    public List<AllTopics> getTopics() {
        StringBuffer queryStr = new StringBuffer("SELECT HD.HEAD_TOPIC_ID as ID ,HD.TOPIC_NAME,HD.TOPIC_TEXT,HD.QUESTION_TYPE "
                + " , (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=ID AND IS_DELETED='F') as commentCount "
                + " FROM head_topic HD WHERE HD.IS_DELETED = 'F' ORDER BY CREATED_DATE DESC");
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

    public List<AllTopics> getTopicsAns() {
        StringBuffer queryStr = new StringBuffer(" SELECT HD.HEAD_TOPIC_ID as ID ,HD.TOPIC_NAME,HD.TOPIC_TEXT,HD.QUESTION_TYPE, "
                + " (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=HD.HEAD_TOPIC_ID AND IS_DELETED='F') as commenntCount "
                + " FROM head_topic HD "
                + " WHERE HD.IS_DELETED = 'F' AND (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=HD.HEAD_TOPIC_ID AND IS_DELETED='F') > 0 "
                + " ORDER BY CREATED_DATE DESC ");
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

    public List<AllTopics> getTopicsNoAns() {
        StringBuffer queryStr = new StringBuffer(" SELECT HD.HEAD_TOPIC_ID as ID ,HD.TOPIC_NAME,HD.TOPIC_TEXT,HD.QUESTION_TYPE, "
                + " (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=HD.HEAD_TOPIC_ID AND IS_DELETED='F') as commenntCount "
                + " FROM head_topic HD "
                + " WHERE HD.IS_DELETED = 'F' AND (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=HD.HEAD_TOPIC_ID AND IS_DELETED='F') = 0 "
                + " ORDER BY CREATED_DATE DESC ");
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
