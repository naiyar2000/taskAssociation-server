package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.TaskType;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.TaskTypeRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    
	@Autowired
	private TaskTypeRepository taskTypeRepository;
	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping("/addTask")
	public TaskType saveTaskType(@RequestBody TaskRequest taskRequest) {
		return taskTypeRepository.save(taskRequest.getTaskType()     );
	}
	@GetMapping("/findTaskTypes")
	public List<TaskType> findTaskTypes(){
		return taskTypeRepository.findAll();
	}
	@GetMapping("/getTaskInfo")
	public List<TaskResponse> getJoinInformation(){
		return taskTypeRepository.getJoinInformation();
	}
}
