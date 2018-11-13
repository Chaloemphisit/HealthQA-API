package com.seproject.healthqa.service;

import com.seproject.healthqa.domain.entity.Comment;
import com.seproject.healthqa.domain.entity.HeadTopic;
import com.seproject.healthqa.domain.repository.CommentRepository;
import com.seproject.healthqa.domain.repository.TopicRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import com.seproject.healthqa.web.bean.Topic;
import com.seproject.healthqa.web.bean.AllTopics;
import com.seproject.healthqa.web.bean.Comments;
import com.seproject.healthqa.web.bean.Profile;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TopicService {

    private static Logger log = Logger.getLogger("InfoLogging");

    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    CommentRepository commentRepository;

    public Topic getTopic(int id_topic) {

        StringBuffer queryStr = new StringBuffer("SELECT HD.HEAD_TOPIC_ID, HD.TOPIC_NAME, HD.TOPIC_TEXT, HD.WEIGHT, HD.HEIGHT, HD.AGE_Y, HD.AGE_M,"
                + " HD.SEX, HD.DISEASE, QUESTION_PURPOSE, HD.QUESTION_TYPE, USERS.USERNAME, CREATED_DATE"
                + " , (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=HD.HEAD_TOPIC_ID AND IS_DELETED='F')"
                + " FROM head_topic HD LEFT JOIN users ON(users.id = HD.id)"
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
        StringBuffer queryStr = new StringBuffer("SELECT COMMENT_ID,COMMENT_TEXT,CREATED_DATE, users.firstname, users.lastname,authority.name"
                + " FROM comment INNER JOIN users ON (comment.COMMENT_ID=users.id)"
                + "             INNER JOIN authority ON(users.AUTHORITY_ID=authority.AUTHORITY_ID)"
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
    
    public HeadTopic createTopic(HeadTopic headTopic) {
        return topicRepository.save(headTopic);
    }


    
//    public boolean isReportTp(int id_user,int id_topic) {
//        
//        HeadTopic table = topicRepository.findAllById(id_topic);
//        if(topicRepository.findOne(id_topic) != null){
//          if((table.getUserId().equals(id_user))&&(table.getIsDeleted().equals('F'))) {
//        	  table.setReportStatus('T');
//          }
//          topicRepository.save(table);
//          return true;
//        }
//        else return false;
//    }
//    
//    public boolean isReportCm(int id_user,int id_comment) {
//    	
//        Comment table = commentRepository.findAllById(id_comment);
//        if(commentRepository.findOne(id_comment) != null){
//          if((table.getUserId().equals(id_user))&&(table.getIsDeleted().equals('F'))) {
//        	  table.setReportStatus('T');
//          }	  
//          commentRepository.save(table);
//          return true;
//        }
//        else return false;
//
//    }
   
    
	
	public List<AllTopics> getUserTopic(int id_user) {
        StringBuffer queryStr = new StringBuffer("SELECT HD.HEAD_TOPIC_ID as ID ,HD.TOPIC_NAME,HD.TOPIC_TEXT,HD.QUESTION_TYPE " 
        		+" , (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=ID AND IS_DELETED='F') as commentCount " 
        		+" FROM head_topic HD WHERE HD.IS_DELETED = 'F' AND USER_ID = "+id_user+" ORDER BY CREATED_DATE DESC");
        List<AllTopics> BeanList = new ArrayList<AllTopics>();
        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
            AllTopics Bean = new AllTopics();
            Bean.setTopicId(Integer.parseInt(obj[0].toString()));
            Bean.setTopicName(obj[1].toString());
            Bean.setTopicText(obj[2].toString());
//            Bean.setQuestion_type(obj[3].toString());
            if ((obj[3].toString()).equals('D')) {
                Bean.setQuestionType("คำถามเฉพาะทางแพทย์");
            } else {
                Bean.setQuestionType("คำถามเฉพาะทางเภสัชกร");
            }

            Bean.setAnswerCount(Integer.parseInt(obj[4].toString()));

            BeanList.add(Bean);
        }
        return BeanList;
    }

	public List<AllTopics> getUserHasCm(int id_user) {
		
        StringBuffer queryStr = new StringBuffer("SELECT HD.HEAD_TOPIC_ID ,HD.TOPIC_NAME,HD.TOPIC_TEXT,HD.QUESTION_TYPE ,"
        		+ " (SELECT COUNT(*) FROM comment WHERE HEAD_TOPIC_ID=HD.HEAD_TOPIC_ID AND IS_DELETED='F') as commentCount "
                        + " FROM head_topic HD JOIN COMMENT cm ON(HD.HEAD_TOPIC_ID = cm.COMMENT_ID) "
        		+ "WHERE HD.IS_DELETED = 'F' AND cm.USER_ID = "+id_user+" ORDER BY HD.CREATED_DATE DESC ");
        List<AllTopics> BeanList = new ArrayList<AllTopics>();
        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
            AllTopics Bean = new AllTopics();
            Bean.setTopicId(Integer.parseInt(obj[0].toString()));
            Bean.setTopicName(obj[1].toString());
            Bean.setTopicText(obj[2].toString());
            if ((obj[3].toString()).equals('D')) {
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
