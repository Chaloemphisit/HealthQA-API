package com.seproject.healthqa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import com.seproject.healthqa.web.bean.Topic;
import com.seproject.healthqa.web.bean.Comment;

@Service
public class QuestionService {

    private static Logger log = Logger.getLogger("InfoLogging");

    @PersistenceContext
    EntityManager entityManager;

    public List<Topic> getTopic(int id_topic) {

        StringBuffer queryStr = new StringBuffer("SELECT HD.TOPIC_NAME,HD.TOPIC_TEXT,HD.WEIGHT,HD.HEIGHT,HD.AGE_Y,HD.AGE_M,"
                + " HD.SEX,HD.DISEASE,HD.QUESTION_TYPE,USER.USERNAME"
                + " , (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=HD.HEAD_TOPIC_ID AND IS_DELETED='F') "
                + " FROM head_topic HD LEFT JOIN user ON(user.USER_ID = HD.USER_ID)"
                + " WHERE (HD.HEAD_TOPIC_ID = " + id_topic + ") AND (HD.IS_DELETED = 'F')");
        List<Topic> BeanList = new ArrayList<Topic>();
        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
            Topic Bean = new Topic();
            Bean.setTopicName(obj[0].toString());
            Bean.setTopicText(obj[1].toString());
            Bean.setWieght(Integer.parseInt(obj[2].toString()));
            Bean.setHeight(Integer.parseInt(obj[3].toString()));
            Bean.setAgeY(Integer.parseInt(obj[4].toString()));
            Bean.setAgeM(Integer.parseInt(obj[5].toString()));
            log.info("Date ----------------------------------------------> " + obj[4]);
//		        	Bean.setAge(obj[4]);
            Bean.setGender(obj[6].toString());
            Bean.setDisease(obj[7].toString());
            Bean.setQuestionType(obj[8].toString());
            Bean.setUsername(obj[9].toString());
            Bean.setCommentCount(obj[10].toString());
            Bean.setComment(getComment(id_topic));
            BeanList.add(Bean);
        }
        return BeanList;

    }

    public List<Comment> getComment(int id_topic) {
        StringBuffer queryStr = new StringBuffer("SELECT COMMENT_ID,COMMENT_TEXT,CREATED_DATE, user.F_NAME, user.L_NAME "
                + " FROM comment JOIN user ON (comment.COMMENT_ID=user.USER_ID)"
                + " WHERE (comment.HEAD_TOPIC_ID = " + id_topic + ") AND (comment.IS_DELETED = 'F')");
        List<Comment> BeanList = new ArrayList<Comment>();
        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
                Comment Bean = new Comment();
                Bean.setCommentId(obj[0].toString());
                Bean.setCommentText(obj[1].toString());
//                Bean.setCreateDate(new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(obj[2].toString())));
                Bean.setName(obj[3].toString()+" "+obj[4].toString());
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
