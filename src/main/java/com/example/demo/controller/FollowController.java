package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Follow;
import com.example.demo.repository.FollowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class FollowController {
    
    @Autowired
    private FollowRepository followRepository;

    @PostMapping("/followTask")
    public Follow saveFollowTask(@RequestBody Follow followRequest) {
        Iterable<Follow> temp = followRepository.findAll();

        for(Follow e: temp) {
            if(e.getTaskId().equals(followRequest.getTaskId())&& e.getUserEmail().equals(followRequest.getUserEmail())) {
                followRepository.delete(e);
                return null;
            }
        }

        return followRepository.save(followRequest);
    }

    @GetMapping("/getFollowTask")
    public Iterable<Follow> getFollowTask() {
        return followRepository.findAll();
    }
}
