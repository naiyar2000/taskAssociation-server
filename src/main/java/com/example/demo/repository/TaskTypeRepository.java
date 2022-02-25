package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.TaskType;

public interface TaskTypeRepository extends JpaRepository<TaskType, Integer>{

	@Query("SELECT new com.example.demo.dto.TaskResponse (t.taskId, t.taskAssignedBy, t.taskTitle, t.taskDescription, tt.taskTypeName) FROM TaskType tt JOIN tt.tasks t")
	public List<TaskResponse> getJoinInformation();
}
