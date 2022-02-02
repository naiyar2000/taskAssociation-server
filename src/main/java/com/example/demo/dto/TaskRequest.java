package com.example.demo.dto;

import com.example.demo.entity.TaskType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskRequest {

	private TaskType taskType;
}
