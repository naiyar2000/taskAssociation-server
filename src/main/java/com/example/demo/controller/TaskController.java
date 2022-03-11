package com.example.demo.controller;

import java.lang.StackWalker.Option;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

// import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.Follow;
import com.example.demo.entity.Task;
// import com.example.demo.entity.TaskType;
import com.example.demo.entity.UserLike;
import com.example.demo.repository.FollowRepository;
import com.example.demo.repository.TaskRepository;
// import com.example.demo.repository.TaskTypeRepository;
import com.example.demo.repository.UserLikeRepository;

@RestController
@CrossOrigin(origins = "*")
public class TaskController {
    
	// @Autowired
	// private TaskTypeRepository taskTypeRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserLikeRepository userLikeRepository;
	@Autowired
	private FollowRepository followRepository;

	@PostMapping("/addTask")
	public Task saveTaskType(@RequestBody Task taskRequest) {
		// return taskTypeRepository.save(taskRequest.getTaskType());
		return taskRepository.save(taskRequest);
	}

	@GetMapping("/findTask")
	public List<Task> findTask(){
		// return taskTypeRepository.findAll();
		return taskRepository.findAll();
	}

	@GetMapping("/getTaskByEmail/{userEmail}")
	public List<Task> findTaskByEmail(@PathVariable("userEmail") String userEmail) {
		List<String> taskIds = new ArrayList<String>();
		Iterable<Follow> followList = followRepository.findByUserEmail(userEmail);

		for(Follow e: followList) {
			taskIds.add(e.getTaskId());
		}

		return taskRepository.findAllById(taskIds);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/getTaskInfo")
	public List<Task> getJoinInformation(){
		// return taskTypeRepository.getJoinInformation();
		return taskRepository.findAll();
	}

	@GetMapping("/getLikeCount/{taskId}")
	public int getLikeCound(@PathVariable("taskId") String taskId) {
		Task task =taskRepository.getById(taskId);
		return task.getLikeCount();
	}

	@PostMapping("/likePost")
	public ResponseEntity<UserLike> saveUserLike(@RequestBody UserLike likeRequest) {
		//TODO: process POST request
		// if(taskRepository.getById(likeRequest.getTask().getTaskId())!=null) {
		// 	taskRepository.getById(likeRequest.getTask().getTaskId()).add(likeRequest);
		// 	// task.add(likeRequest);
		// 	// taskRepository.save(task);
		// 	return userLikeRepository.save(likeRequest);
		// }

		Optional<Task> tempTask = taskRepository.findById(likeRequest.getTask().getTaskId());

		likeRequest.setTask(tempTask.get());

		List<UserLike> tempUserLike = (List<UserLike>)userLikeRepository.findAll();

		for(UserLike x: tempUserLike) {
			if(x.getUserEmail().equals(likeRequest.getUserEmail()) 
			&& x.getTask().getTaskId().equals(likeRequest.getTask().getTaskId())) {
				return null;
			}
		}

		UserLike userLike = userLikeRepository.save(likeRequest);

		Task tt = tempTask.get();
		
		tt.updateTaskLikeCount();

		taskRepository.save(tt);


		return ResponseEntity.ok(userLike);
		// return userLikeRepository.save(likeRequest);
	}
	
}
