package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class TaskType {
   
	@Id
	@GeneratedValue
	private int taskTypeId;
	private String taskTypeName;
	@OneToMany(targetEntity = Task.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "taskTypeId", referencedColumnName = "taskTypeId")
	private List<Task> tasks;
}
