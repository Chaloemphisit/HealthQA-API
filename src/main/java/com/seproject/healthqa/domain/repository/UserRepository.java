package com.seproject.healthqa.domain.repository;

import com.seproject.healthqa.domain.entity.Users;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
    
    Optional<Users> findByEmail(String email);

    Optional<Users> findByUsernameOrEmail(String username, String email);

    List<Users> findByIdIn(List<Integer> userIds);

    Optional<Users> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
