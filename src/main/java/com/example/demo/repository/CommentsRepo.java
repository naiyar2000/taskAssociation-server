package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Comments;


@Repository
public interface CommentsRepo extends JpaRepository<Comments, Integer>{
	
	Comments save(Comments comment);
	
	ArrayList<Comments> findAllByTaskId(String taskId);

}