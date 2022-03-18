package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity
public class UserProfile {
    
    @Id
    private String userEmail;

    @Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;

    public String getUserEmail() {
        return userEmail;
    }

    public UserProfile() {
        super();
    }

    public UserProfile(String userEmail, String image) {
        this.userEmail = userEmail;
        this.image = image;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
}
