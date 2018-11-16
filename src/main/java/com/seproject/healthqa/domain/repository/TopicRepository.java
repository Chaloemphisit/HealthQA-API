package com.seproject.healthqa.domain.repository;



import org.springframework.data.repository.CrudRepository;

import com.seproject.healthqa.domain.entity.HeadTopic;


public interface TopicRepository extends CrudRepository<HeadTopic,Integer>{

	
}
