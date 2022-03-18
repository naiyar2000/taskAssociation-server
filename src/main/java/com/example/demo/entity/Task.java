package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Task {
	
	@Id
	private String taskId;
	private String taskType;
	private String taskAssignedBy;
	private String taskTitle;
	private String taskDescription;
	private int likeCount;

	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	@OneToMany(mappedBy = "task", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JsonIgnoreProperties("task")
	List<UserLike> userLike = new ArrayList<UserLike>();

	public List<UserLike> getUserLike() {
		return userLike;
	}
	public void setUserLike(List<UserLike> userLike) {
		this.userLike = userLike;
	}


	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskAssignedBy() {
		return taskAssignedBy;
	}
	public void setTaskAssignedBy(String taskAssignedBy) {
		this.taskAssignedBy = taskAssignedBy;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public int getLikeCount() {
		return likeCount;
	}
	
	public Task() {
		super();
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public Task(String taskId, String taskAssignedBy, String taskTitle, String taskDescription, int likeCount,
			List<UserLike> userLike) {
		this.taskId = taskId;
		this.taskAssignedBy = taskAssignedBy;
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.likeCount = likeCount;
		this.userLike = userLike;
	}
	public Task(String taskId, String taskAssignedBy, String taskTitle, String taskDescription, int likeCount) {
		this.taskId = taskId;
		this.taskAssignedBy = taskAssignedBy;
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.likeCount = likeCount;
	}

	public Task(String taskId, String taskType, String taskAssignedBy, String taskTitle, String taskDescription,
			int likeCount, List<UserLike> userLike) {
		this.taskId = taskId;
		this.taskType = taskType;
		this.taskAssignedBy = taskAssignedBy;
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.likeCount = likeCount;
		this.userLike = userLike;
	}
	public void add (UserLike tempUserLike) {
		if(userLike==null) {
			userLike = new ArrayList<>();
		}

		userLike.add(tempUserLike);
	
		tempUserLike.setTask(this);
	}

	public void updateTaskLikeCount() {
		this.likeCount++;
	}

	public void updateTaskUnlikeCount() {
		this.likeCount--;
	}
	
}
