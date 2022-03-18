package com.example.demo.controller;

import java.lang.StackWalker.Option;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.stream.events.Comment;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

// import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.Comments;
import com.example.demo.entity.Follow;
import com.example.demo.entity.Task;
// import com.example.demo.entity.TaskType;
import com.example.demo.entity.UserLike;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.CommentsRepo;
import com.example.demo.repository.FollowRepository;
import com.example.demo.repository.TaskRepository;
// import com.example.demo.repository.TaskTypeRepository;
import com.example.demo.repository.UserLikeRepository;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProfileService;

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
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentsRepo commentsRepo;
	@Autowired
	private ProfileService profileService;

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

	@GetMapping("/getLikeCount/{taskId}/{userEmail}")
	public ResponseEntity<Map<String, Object>> getLikeCount(@PathVariable("taskId") String taskId, @PathVariable("userEmail") String email) {
		Task task =taskRepository.getById(taskId);
		List<UserLike> userlike = userLikeRepository.findByTask(task);
		List<Comments> comments = commentsRepo.findAllByTaskId(taskId);
		List<Follow> followers = followRepository.findByTaskId(taskId);
		String user_Email = userlike.size()>0 ? userlike.get(0).getUserEmail() : "";
		String userName = userlike.size()>0 ?userRepository.findByEmail(user_Email).getUsername() : "";

		//to check if user has already liked a post
		List<UserLike> tempUserLike = (List<UserLike>)userLikeRepository.findAll();
		List<Follow> tempUserFollow = (List<Follow>)followRepository.findAll();

		Boolean hasLiked = false;
		Boolean hasFollowed = false;
		for(UserLike x: tempUserLike) {
			if(x.getUserEmail().equals(email) 
			&& x.getTask().getTaskId().equals(taskId)) {
				hasLiked=true;
			}
		}
		for(Follow x: tempUserFollow) {
			if(x.getUserEmail().equals(email) 
			&& x.getTaskId().equals(taskId)) {
				hasFollowed=true;
			}
		}
		
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		if(hasLiked)
			response.put("hasLiked", true);
		else 
			response.put("hasLiked", false);
		if(hasFollowed)
			response.put("hasFollowed", true);
		else 
			response.put("hasFollowed", false);

		response.put("likes", task.getLikeCount());
		response.put("firstName", userName);
		response.put("numOfComments", comments.size());
		response.put("numOfFollowers", followers.size());
		return ResponseEntity.ok(response);
	}

	@PostMapping(value="/postProfilePic/{userEmail}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
	public ResponseEntity<UserProfile> saveProduct(@RequestParam("file") MultipartFile file,
	@PathVariable("userEmail") String userEmail) {
		return ResponseEntity.ok(profileService.saveProfileToDB(file, userEmail));
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
				userLikeRepository.delete(x);
				Task tt = tempTask.get();
				if(tt.getLikeCount()>0) {
					tt.updateTaskUnlikeCount();
					taskRepository.save(tt);
				}
				return ResponseEntity.ok(null);
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
