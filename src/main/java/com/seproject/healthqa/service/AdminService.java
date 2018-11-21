/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seproject.healthqa.service;

import com.seproject.healthqa.web.bean.AllTopics;
import com.seproject.healthqa.web.payload.PagedResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author 59050320
 */

@Service
public class AdminService {
    
    private static Logger log = Logger.getLogger("InfoLogging");

    @PersistenceContext
    EntityManager entityManager;


    public PagedResponse<AllTopics> getRpTopics(int page, int size) {
        StringBuffer queryStr = new StringBuffer("SELECT HD.HEAD_TOPIC_ID as ID ,HD.TOPIC_NAME,HD.TOPIC_TEXT,HD.QUESTION_TYPE "
                + " , (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=ID AND IS_DELETED='F') as commentCount "
                + " FROM head_topic HD WHERE HD.IS_DELETED = 'F' AND HD.REPORT_STATUS = 'T' ORDER BY CREATED_DATE DESC");

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
    
        
        
}
