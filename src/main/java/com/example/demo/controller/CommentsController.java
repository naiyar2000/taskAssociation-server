package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CommentsService;
import com.example.demo.entity.Comments;


@RestController
@RequestMapping("/comments")
public class CommentsController {
	
	@Autowired
	CommentsService commentsService;
	
	@PostMapping("")
	private Comments submitComment(@RequestBody Comments comment) {
		return commentsService.submitCommentToDB(comment);
	}
	
	@GetMapping("/{taskId}")
	private ArrayList<Comments> getCommentsForTask(@PathVariable("taskId") String taskId){
		return commentsService.getAllCommentsForDB(taskId);
	}
	

}
