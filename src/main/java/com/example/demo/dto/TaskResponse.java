package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskResponse {

	private String taskAssignedBy;
	private String taskTitle;
	private String taskDescription;
	private String taskTypeName;
}
