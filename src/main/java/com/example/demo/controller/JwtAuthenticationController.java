package com.example.demo.controller;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.dto.JwtRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.entity.UserDao;
import com.example.demo.entity.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class JwtAuthenticationController {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		UserDao email = userDao.findByEmail(authenticationRequest.getUsername());
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		System.out.print(userDetails.getUsername());
		return ResponseEntity.ok(new JwtResponse(token, email.getUsername(), email.getEmail()));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
		UserDao email = userDao.findByEmail(user.getEmail());
		if(email==null) {
			
			ResponseEntity.ok(userDetailsService.save(user));
			authenticate(user.getEmail(), user.getPassword());
	
			final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
	
			final String token = jwtTokenUtil.generateToken(userDetails);
	
			
			return ResponseEntity.ok(new JwtResponse(token, user.getUsername(), user.getEmail()));

		} else {
			return null;
		}
			
	}
	
	// @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	// public User

	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
