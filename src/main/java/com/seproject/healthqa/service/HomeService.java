package com.seproject.healthqa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import com.seproject.healthqa.domain.repository.CommentRepository;
import com.seproject.healthqa.web.bean.AllTopics;
import com.seproject.healthqa.domain.entity.Comment;

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
	          	          
	          for(Object[] obj:objectList){
	        	AllTopics Bean = new AllTopics();
	        	Bean.setHead_topic_id(Integer.parseInt(obj[0].toString()));
	        	Bean.setTopic_name(obj[1].toString());
	        	Bean.setTopic_text(obj[2].toString());
	        	Bean.setQuestion_type(obj[3].toString());
	        	Bean.setAnswer(Integer.parseInt(obj[4].toString()));
	            BeanList.add(Bean);
	           }
	          return BeanList;
	}
	

	
}