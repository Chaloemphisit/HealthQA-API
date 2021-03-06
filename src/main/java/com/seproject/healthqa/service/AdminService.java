/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seproject.healthqa.service;

import com.seproject.healthqa.domain.entity.Users;
import com.seproject.healthqa.domain.repository.UserRepository;
import com.seproject.healthqa.security.UserPrincipal;
import com.seproject.healthqa.web.bean.Person;
import com.seproject.healthqa.web.bean.ReportTopicResponse;
import com.seproject.healthqa.web.payload.AdminUserResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    UserRepository userRepository;
    
    public List<ReportTopicResponse> getTopic(UserPrincipal currentUser) {
        StringBuffer queryStr = new StringBuffer("SELECT HD.HEAD_TOPIC_ID as ID ,HD.TOPIC_NAME,HD.TOPIC_TEXT"
                + " FROM head_topic HD WHERE HD.IS_DELETED = 'F' "
                + " ORDER BY CREATED_DATE DESC");
        
        List<ReportTopicResponse> BeanList = new ArrayList<ReportTopicResponse>();
        
        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();
        
        for (Object[] obj : objectList) {
            ReportTopicResponse Bean = new ReportTopicResponse();
            Bean.setId(Long.parseLong(obj[0].toString()));
            Bean.setTopicName(obj[1].toString());
            Bean.setTopicText(obj[2].toString());
//            Bean.setQuestion_type(obj[3].toString());
            BeanList.add(Bean);
        }
        return BeanList;
    }
    
    public List<Person> getDoctor() {
        StringBuffer queryStr = new StringBuffer("SELECT u.id , u.firstname, u.lastname , u.email, u.username"
                + " FROM user_authority ua LEFT JOIN users u ON(ua.user_id = u.id) "
                + " WHERE u.is_deleted = 0 AND ua.authority_id = 2 AND ua.authority_id != 3 "
                + " ORDER BY u.id ");
        
        List<Person> BeanList = new ArrayList<Person>();
        
        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();
        
        for (Object[] obj : objectList) {
            Person Bean = new Person();
            Bean.setId(Long.parseLong(obj[0].toString()));
            Bean.setFirstName(obj[1].toString());
            Bean.setLastName(obj[2].toString());
            Bean.setEmail(obj[3].toString());
            Bean.setUsername(obj[4].toString());
            
            BeanList.add(Bean);
        }
        return BeanList;
    }
    
    public List<Person> getAdmin() {
        StringBuffer queryStr = new StringBuffer("SELECT u.id , u.firstname, u.lastname , u.email, u.username"
                + " FROM user_authority ua JOIN users u ON(ua.user_id = u.id) "
                + " WHERE u.is_deleted = 0 AND ua.authority_id = 3 AND ua.authority_id != 2 "
                + " ORDER BY u.id ");
        
        List<Person> BeanList = new ArrayList<Person>();
        
        Query query = entityManager.createNativeQuery(queryStr.toString());
        List<Object[]> objectList = query.getResultList();
        
        for (Object[] obj : objectList) {
            Person Bean = new Person();
            Bean.setId(Long.parseLong(obj[0].toString()));
            Bean.setFirstName(obj[1].toString());
            Bean.setLastName(obj[2].toString());
            Bean.setEmail(obj[3].toString());
            Bean.setUsername(obj[4].toString());
            BeanList.add(Bean);
        }
        return BeanList;
    }
    
    public AdminUserResponse getUsers() {
        AdminUserResponse adminUserResponse = new AdminUserResponse();
        adminUserResponse.setAdmin(getAdmin());
        adminUserResponse.setDoctor(getDoctor());
        
        return adminUserResponse;
    }
    
    public Optional<Users> deleteUser(String username) {
        Optional<Users> OptUser = userRepository.findByUsername(username);
        log.info("\n\n\n\n\n------------------------>" + username);
        if (!OptUser.isPresent()) {
            return OptUser;
        }
        
        Users user = OptUser.get();
        user.setIsDeleted(true);
        
        System.out.println("\n\n\n\n\n\n\n\n\n"
                + "" + user.getUsername()
                + "\n\n\n\n\n\n\n\n\n\n");
        
        return Optional.of(userRepository.save(user));
        
    }
}
