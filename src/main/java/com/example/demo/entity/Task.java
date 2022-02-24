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
   
	@Id
	private String taskId;
	private String taskAssignedBy;
	private String taskTitle;
	private String taskDescription;
	private int likeCount;
	
}
