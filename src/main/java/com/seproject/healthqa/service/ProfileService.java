package com.seproject.healthqa.service;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import com.seproject.healthqa.web.bean.Profile;
import com.seproject.healthqa.domain.entity.Users;
import com.seproject.healthqa.domain.repository.UserRepository;
import com.seproject.healthqa.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProfileService {

    private static Logger log = Logger.getLogger("InfoLogging");

    @Autowired
    private UserRepository userRepository;
    
    @PersistenceContext
    EntityManager entityManager;

//    public Profile getProfile(Long id_user, UserPrincipal currentUser) {
//
//        if (!Objects.equals(currentUser.getId(), id_user)) {
//            throw new ForbiddenException("Forbidden!");
//        }
//
//        String queryStr = "SELECT id, username, firstname, lastname, email, password "
//                + " FROM users "
//                + " WHERE id = " + id_user + " AND is_deleted = 'F' ";
//        Profile profile = new Profile();
//        Query query = entityManager.createNativeQuery(queryStr);
//        List<Object[]> objectList = query.getResultList();
//
//        for (Object[] obj : objectList) {
//            profile.setId(Long.parseLong(obj[0].toString()));
//            profile.setUsername(obj[1].toString());
//            profile.setFirstname(obj[2].toString());
//            profile.setLastname(obj[3].toString());
//            profile.setEmail(obj[4].toString());
//            profile.setPassword("********");
//        }
//        return profile;
//    }
    
    public Profile getProfile(String username){
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        Profile profile = new Profile(user.getId(), user.getUsername(), user.getFirstName(),user.getLastName(), user.getEmail(), user.getPassword());
        
        return profile;
        
    }

}
