package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.UserTaskType;

import org.springframework.data.repository.CrudRepository;

public interface UserTaskTypeRepository extends CrudRepository<UserTaskType, Integer> {
    public List<UserTaskType> findByUserEmailAndTaskType(String userEmail, String taskType);
    public List<UserTaskType> findByTaskType(String taskType);
    public List<UserTaskType> findByUserEmail(String userEmail);
}
