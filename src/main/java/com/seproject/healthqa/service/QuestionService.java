package com.seproject.healthqa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import com.seproject.healthqa.web.bean.Topic;
import com.seproject.healthqa.web.bean.Comments;
import java.util.Date;

@Service
public class QuestionService {

    private static Logger log = Logger.getLogger("InfoLogging");

    @PersistenceContext
    EntityManager entityManager;

    public Topic getTopic(int id_topic) {

        StringBuffer queryStr = new StringBuffer("SELECT HD.HEAD_TOPIC_ID, HD.TOPIC_NAME, HD.TOPIC_TEXT, HD.WEIGHT, HD.HEIGHT, HD.AGE_Y, HD.AGE_M,"
                + " HD.SEX, HD.DISEASE, QUESTION_PURPOSE, HD.QUESTION_TYPE, USER.USERNAME, CREATED_DATE"
                + " , (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=HD.HEAD_TOPIC_ID AND IS_DELETED='F')"
                + " FROM head_topic HD LEFT JOIN user ON(user.USER_ID = HD.USER_ID)"
                + " WHERE (HD.HEAD_TOPIC_ID = " + id_topic + ") AND (HD.IS_DELETED = 'F')");
        Topic topic = new Topic();
        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
            topic.setTopicId(obj[0].toString());
            topic.setTopicName(obj[1].toString());
            topic.setTopicText(obj[2].toString());
            topic.setWeight(Integer.parseInt(obj[3].toString()));
            topic.setHeight(Integer.parseInt(obj[4].toString()));
            topic.setAgeY(Integer.parseInt(obj[5].toString()));
            topic.setAgeM(Integer.parseInt(obj[6].toString()));

            if ((obj[7].toString()).equals('M')) {
                topic.setGender("ชาย");
            } else {
                topic.setGender("หญิง");
            }
            
            topic.setDisease(obj[8].toString());
            topic.setQuestionPurpose(obj[9].toString());
            
            if ((obj[10].toString()).equals('D')) {
                topic.setQuestionType("คำถามเฉพาะทางแพทย์");
            } else {
                topic.setQuestionType("คำถามเฉพาะทางเภสัชกร");
            }
            
            topic.setUsername(obj[11].toString());
            topic.setCreateDate((java.sql.Timestamp) obj[12]);
            topic.setAnswerCount(obj[13].toString());
            topic.setComments(getComment(id_topic));
        }
        return topic;

    }

    public List<Comments> getComment(int id_topic) {
        StringBuffer queryStr = new StringBuffer("SELECT COMMENT_ID,COMMENT_TEXT,CREATED_DATE, user.F_NAME, user.L_NAME,authority.AUTHORITY_NAME"
                + " FROM comment INNER JOIN user ON (comment.COMMENT_ID=user.USER_ID)"
                + "             INNER JOIN authority ON(user.AUTHORITY_ID=authority.AUTHORITY_ID)"
                + " WHERE (comment.HEAD_TOPIC_ID = " + id_topic + ") AND (comment.IS_DELETED = 'F')");
        List<Comments> BeanList = new ArrayList<Comments>();
        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
            Comments Bean = new Comments();
            Bean.setCommentId(obj[0].toString());
            Bean.setCommentText(obj[1].toString());
//                Bean.setCreateDate(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(obj[2].toString())));
            Bean.setCreateDate((Date) obj[2]);
            Bean.setName(obj[3].toString() + " " + obj[4].toString());
            Bean.setUserType(obj[5].toString());
            BeanList.add(Bean);

        }
        return BeanList;
    }

//    		public int calculateAgeWithJava7(
//    		  Date birthDate, 
//    		  Date currentDate) {            
//    		    // validate inputs ...                                                                               
//    		    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
//    		    int d1 = Integer.parseInt(formatter.format(birthDate));                            
//    		    int d2 = Integer.parseInt(formatter.format(currentDate));                          
//    		    int age = (d2 - d1) / 10000;                                                       
//    		    return age;                                                                        
//    		}
}
