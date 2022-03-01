package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.Task;
import com.example.demo.entity.TaskType;
import com.example.demo.entity.UserLike;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.TaskTypeRepository;
import com.example.demo.repository.UserLikeRepository;

@RestController
@CrossOrigin(origins = "*")
public class TaskController {
    
	@Autowired
	private TaskTypeRepository taskTypeRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserLikeRepository userLikeRepository;
	
	@PostMapping("/addTask")
	public TaskType saveTaskType(@RequestBody TaskRequest taskRequest) {
		return taskTypeRepository.save(taskRequest.getTaskType());
	}
	@GetMapping("/findTaskTypes")
	public List<TaskType> findTaskTypes(){
		return taskTypeRepository.findAll();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/getTaskInfo")
	public List<TaskResponse> getJoinInformation(){
		return taskTypeRepository.getJoinInformation();
	}

	@PostMapping("/likePost")
	public List<UserLike> saveUserLike(@RequestBody UserLike likeRequest) {
		//TODO: process POST request

		// Task task = taskTypeRepository(likeRequest.getTaskId());
		// System.out.print(task);
		// .getUserLike().add(likeRequest);

		return userLikeRepository.findAll();
		// return userLikeRepository.save(likeRequest);
	}
	
}
