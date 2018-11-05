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

    public List<Topic> getTopic(int id_topic) {

        StringBuffer queryStr = new StringBuffer("SELECT HD.HEAD_TOPIC_ID, HD.TOPIC_NAME, HD.TOPIC_TEXT, HD.WEIGHT, HD.HEIGHT, HD.AGE_Y, HD.AGE_M,"
                + " HD.SEX, HD.DISEASE, QUESTION_PURPOSE, HD.QUESTION_TYPE, USER.USERNAME, CREATED_DATE"
                + " , (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=HD.HEAD_TOPIC_ID AND IS_DELETED='F')"
                + " FROM head_topic HD LEFT JOIN user ON(user.USER_ID = HD.USER_ID)"
                + " WHERE (HD.HEAD_TOPIC_ID = " + id_topic + ") AND (HD.IS_DELETED = 'F')");
        List<Topic> BeanList = new ArrayList<Topic>();
        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
            Topic Bean = new Topic();
            Bean.setTopicId(obj[0].toString());
            Bean.setTopicName(obj[1].toString());
            Bean.setTopicText(obj[2].toString());
            Bean.setWeight(Integer.parseInt(obj[3].toString()));
            Bean.setHeight(Integer.parseInt(obj[4].toString()));
            Bean.setAgeY(Integer.parseInt(obj[5].toString()));
            Bean.setAgeM(Integer.parseInt(obj[6].toString()));

            if ((obj[7].toString()).equals('M')) {
                Bean.setGender("ชาย");
            } else {
                Bean.setGender("หญิง");
            }
            
            Bean.setDisease(obj[8].toString());
            Bean.setQuestionPurpose(obj[9].toString());
            
            if ((obj[10].toString()).equals('D')) {
                Bean.setQuestionType("คำถามเฉพาะทางแพทย์");
            } else {
                Bean.setQuestionType("คำถามเฉพาะทางเภสัชกร");
            }
            
            Bean.setUsername(obj[11].toString());
            Bean.setCreateDate((java.sql.Timestamp) obj[12]);
            Bean.setAnswerCount(obj[13].toString());
            Bean.setComments(getComment(id_topic));
            BeanList.add(Bean);
        }
        return BeanList;

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
