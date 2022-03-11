package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, String>{
}
