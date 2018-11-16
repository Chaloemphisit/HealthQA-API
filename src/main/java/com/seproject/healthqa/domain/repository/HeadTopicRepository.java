package com.seproject.healthqa.domain.repository;

import com.seproject.healthqa.domain.entity.HeadTopic;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadTopicRepository extends CrudRepository<HeadTopic, Integer> {

    Optional<HeadTopic> findByHeadTopicId(Integer headTopicId);

    public boolean existsByHeadTopicId(Integer headTopicId);
}
