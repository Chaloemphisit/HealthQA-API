package com.seproject.healthqa.service;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import com.seproject.healthqa.web.bean.Profile;
import com.seproject.healthqa.domain.entity.Users;
import com.seproject.healthqa.domain.repository.UserRepository;
import com.seproject.healthqa.exception.CustomException;
import com.seproject.healthqa.exception.ResourceNotFoundException;
import com.seproject.healthqa.security.UserPrincipal;
import com.seproject.healthqa.web.payload.ApiResponse;
import com.seproject.healthqa.web.payload.EditProfileRequest;
import com.seproject.healthqa.web.payload.PasswordIdentity;
import java.sql.Timestamp;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class ProfileService {

    private static Logger log = Logger.getLogger("InfoLogging");

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PasswordEncoder passwordEncoder;

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
    public Profile getProfile(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        Profile profile = new Profile(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());

        return profile;

    }

    public ResponseEntity<?> editProfile(UserPrincipal currentUser, EditProfileRequest profile) {
        Optional<Users> oldUserData = userRepository.findById(currentUser.getId());

//        if (!profile.getNewPassword().isEmpty()) {
//            if (checkUserPassword(currentUser.getId(), profile.getOldPassword()).isVerified()) {
        Users user = new Users();

        user.setId(currentUser.getId());
        user.setFirstName(profile.getFirstname().isEmpty() ? oldUserData.get().getFirstName() : profile.getFirstname());
        user.setLastName(profile.getLastname().isEmpty() ? oldUserData.get().getLastName() : profile.getLastname());
        user.setEmail(profile.getEmail().isEmpty() ? oldUserData.get().getEmail() : profile.getEmail());
        user.setUsername(currentUser.getUsername());
//        user.setPassword(oldUserData.get().getPassword());
//        user.setPassword(passwordEncoder.encode(profile.getNewPassword()));
        return ResponseEntity.ok(userRepository.save(user));
//            }
//        }
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomException(new Timestamp(System.currentTimeMillis()), 400, "Bad Request", "รหัสผ่านเดิมไม่ภูกต้อง"));
    }

    public PasswordIdentity checkUserPassword(Long id, String passoword) {

        Optional<Users> user = userRepository.findById(id);
//
//        if (user.isPresent()) {
//            if (user.get().getPassword().equals(passwordEncoder.encode(passoword))) {
//                return new PasswordIdentity((true));
//            }
//        }
//        return new PasswordIdentity((false));
        System.err.println("\n\n\n\n---------------------------------------------------------->\n"
                + "" + user.get().getPassword()
                + "\n<------------------------------------------------------------\n\n\n\n\n\n");
        return null;
    }

}
