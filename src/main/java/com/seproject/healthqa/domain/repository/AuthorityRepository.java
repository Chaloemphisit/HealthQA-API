package com.seproject.healthqa.domain.repository;

import com.seproject.healthqa.domain.RoleName;
import com.seproject.healthqa.domain.entity.Authority;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Optional<Authority> findByName(RoleName roleName);
}
