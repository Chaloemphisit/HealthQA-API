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
import com.seproject.healthqa.web.bean.Profile;
import com.seproject.healthqa.web.bean.Topic;
import com.seproject.healthqa.domain.entity.Comment;

@Service
public class ProfileService {

    private static Logger log = Logger.getLogger("InfoLogging");

    @PersistenceContext
    EntityManager entityManager;
    
    public Profile getProfile(int id_user) {
        StringBuffer queryStr = new StringBuffer("SELECT USER_ID, USERNAME, F_NAME, L_NAME, EMAIL, PASSWORD " + 
        		" FROM USER " + 
        		" WHERE USER_ID = '1' AND IS_DELETED = 'F' ");
        Profile profile = new Profile();
        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();

        for (Object[] obj : objectList) {
            profile.setUserId(Integer.parseInt(obj[0].toString()));
            profile.setUsername(obj[1].toString());
            profile.setfName(obj[2].toString());
            profile.setlName(obj[3].toString());
            profile.setEmail(obj[4].toString());
            profile.setPassword("********");
        }
        return profile;
    }
    
    
}