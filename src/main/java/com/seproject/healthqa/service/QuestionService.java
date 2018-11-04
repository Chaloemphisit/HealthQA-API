package com.seproject.healthqa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import com.seproject.healthqa.domain.repository.CommentRepository;
import com.seproject.healthqa.web.bean.AllTopics;
import com.seproject.healthqa.web.bean.Topic;
import com.seproject.healthqa.domain.entity.Comment;

@Service
public class QuestionService {
	
	private static Logger log = Logger.getLogger("InfoLogging");
	
    @PersistenceContext
    EntityManager entityManager;
    
    public List<Topic> getTopic(int id_topic) {
    	
	      StringBuffer queryStr = new StringBuffer("SELECT HD.TOPIC_NAME,HD.TOPIC_TEXT,HD.WEIGHT,HD.HEIGHT,HD.Birthday," + 
	      		" HD.SEX,HD.DISEASE,HD.QUESTION_TYPE,USER.USERNAME" + 
	      		" FROM head_topic HD LEFT JOIN user ON(user.USER_ID = HD.USER_ID)" + 
	      		" WHERE (HD.HEAD_TOPIC_ID = "+id_topic+") AND (HD.IS_DELETED = 'F')");
		          List<Topic> BeanList = new ArrayList<Topic>();
		          Query query = entityManager.createNativeQuery(queryStr.toString());
		          List<Object[]> objectList = query.getResultList();

		          for(Object[] obj:objectList){
		        	Topic Bean = new Topic();
		        	Bean.setTOPIC_NAME(obj[0].toString());
		        	Bean.setTOPIC_TEXT(obj[1].toString());
		        	Bean.setWEIGHT(Integer.parseInt(obj[2].toString()));
		        	Bean.setHEIGHT(Integer.parseInt(obj[3].toString()));
		        	log.info("Date ----------------------------------------------> "+obj[4]);
//		        	Bean.setAge(obj[4]);
		        	Bean.setSEX(obj[5].toString());
		        	Bean.setDISEASE(obj[6].toString());
		        	Bean.setQUESTION_TYPE(obj[7].toString());
		        	Bean.setUSERNAME(obj[8].toString());
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