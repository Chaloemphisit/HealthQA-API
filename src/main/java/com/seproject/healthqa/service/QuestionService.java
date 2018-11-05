package com.seproject.healthqa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.seproject.healthqa.web.bean.Topic;

@Service
public class QuestionService {
	
	private static Logger log = Logger.getLogger("InfoLogging");
	
    @PersistenceContext
    EntityManager entityManager;
    
    public List<Topic> getTopic(int id_topic) {
    	
	      StringBuffer queryStr = new StringBuffer("SELECT HD.TOPIC_NAME,HD.TOPIC_TEXT,HD.WEIGHT,HD.HEIGHT,HD.AGE," + 
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
		        	Bean.setAge(Double.parseDouble(obj[4].toString()));
		        	
		        	if((obj[5].toString()).equals('M')) Bean.setSEX("Male");
		        	else Bean.setSEX("Female");
		        	
		        	Bean.setDISEASE(obj[6].toString());
		        	
		        	if((obj[7].toString()).equals('D')) Bean.setQUESTION_TYPE("Doctor");
		        	else Bean.setQUESTION_TYPE("Pharmacy");
		        	
		        	Bean.setUSERNAME(obj[8].toString());
		            BeanList.add(Bean);
		           }
		  return BeanList;
   
    }
    


}