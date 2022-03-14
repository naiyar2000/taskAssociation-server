package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Task;
import com.example.demo.entity.UserLike;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikeRepository extends JpaRepository<UserLike, Integer>{
    public List<UserLike> findByTask(Task task);
}
