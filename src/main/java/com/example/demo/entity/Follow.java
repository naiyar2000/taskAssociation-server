package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Follow {
    @Id
    @GeneratedValue
    private int id;
    
    private String taskId;
    private String userEmail;
    public Follow() {
        super();
    }
    public Follow(int id, String taskId, String userEmail) {
        this.id = id;
        this.taskId = taskId;
        this.userEmail = userEmail;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    
    
}
