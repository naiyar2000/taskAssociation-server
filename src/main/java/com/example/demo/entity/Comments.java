package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="Comments")
public class Comments {
	@Id
	@GeneratedValue
	private int id;
	
	private String taskId;
//	private String commentId;
	private String userId;
	private String comment;
	private Timestamp timeStamp;
	private int likeCount;
	
	

}
