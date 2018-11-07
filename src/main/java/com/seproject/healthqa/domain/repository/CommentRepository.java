package com.seproject.healthqa.domain.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.seproject.healthqa.domain.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer>{



}