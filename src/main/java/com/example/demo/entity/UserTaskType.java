package com.example.demo.entity;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserTaskType {
    @Id
    private int id;
    private String userEmail;
    private String taskType;


}
