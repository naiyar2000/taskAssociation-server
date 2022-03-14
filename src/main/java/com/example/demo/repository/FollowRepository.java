package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Follow;

import org.springframework.data.repository.CrudRepository;

public interface FollowRepository extends CrudRepository<Follow, Integer> {
    public List<Follow> findByUserEmail(String userEmail); 
    public List<Follow> findByTaskId(String taskId);
}
