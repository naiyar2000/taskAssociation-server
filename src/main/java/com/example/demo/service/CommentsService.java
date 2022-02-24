package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Comments;
import com.example.demo.entity.Task;
import com.example.demo.repository.CommentsRepo;

@Service
public class CommentsService {
	@Autowired
	CommentsRepo commentsRepo;
	
	public Comments submitCommentToDB(Comments comment) {
		return commentsRepo.save(comment);
		
	}

	public ArrayList<Comments>  getAllCommentsForDB(String taskId){
		ArrayList<Comments> commentList = commentsRepo.findAllByTaskId(taskId);
		return commentList;
		
	}
}
