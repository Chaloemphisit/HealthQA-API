package com.seproject.healthqa.domain.repository;

import com.seproject.healthqa.domain.entity.HeadTopic;
import com.seproject.healthqa.domain.entity.Users;
import com.seproject.healthqa.web.bean.Topic;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<HeadTopic, Integer> {

    Optional<HeadTopic> findByHeadTopicId(int headTopicId);
}
