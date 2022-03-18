package com.example.demo.service;

import java.io.IOException;
import java.util.Base64;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

	public UserProfile getProfileFromDB(String userEmail) {
		return userProfileRepository.findByUserEmail(userEmail);
	}

    public UserProfile saveProfileToDB(MultipartFile file,String userEmail)
	{
		UserProfile temp = userProfileRepository.findByUserEmail(userEmail);
		if(temp==null) {

			UserProfile p = new UserProfile();
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if(fileName.contains(".."))
			{
				System.out.println("not a a valid file");
			}
			try {
				p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.setUserEmail(userEmail);
			return userProfileRepository.save(p);
		} else {
			UserProfile p = userProfileRepository.findByUserEmail(userEmail);
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if(fileName.contains(".."))
			{
				System.out.println("not a a valid file");
			}
			try {
				p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return userProfileRepository.save(p);
		}
		      
	}
}
