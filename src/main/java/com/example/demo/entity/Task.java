package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Task {
   
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
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public Task(String taskId, String taskAssignedBy, String taskTitle, String taskDescription, int likeCount) {
		this.taskId = taskId;
		this.taskAssignedBy = taskAssignedBy;
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.likeCount = likeCount;
	}
	@Id
	private String taskId;
	private String taskAssignedBy;
	private String taskTitle;
	private String taskDescription;
	private int likeCount;
	
}
