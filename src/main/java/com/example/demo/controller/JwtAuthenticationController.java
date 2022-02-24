package com.example.demo.controller;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.dto.JwtRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.TaskType;
import com.example.demo.entity.UserDao;
import com.example.demo.entity.UserDto;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.TaskTypeRepository;
import com.example.demo.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private TaskTypeRepository taskTypeRepository;
	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping("/addTask")
	public TaskType saveTaskType(@RequestBody TaskRequest taskRequest) {
		return taskTypeRepository.save(taskRequest.getTaskType()     );
	}
	@GetMapping("/findTaskTypes")
	public List<TaskType> findTaskTypes(){
		return taskTypeRepository.findAll();
	}
	
	@RequestMapping(value = "/getTaskInfo", method = RequestMethod.GET)
	public List<TaskResponse> getJoinInformation(){
		return taskTypeRepository.getJoinInformation();
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
		 ResponseEntity.ok(userDetailsService.save(user));
			authenticate(user.getUsername(), user.getPassword());

			final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new JwtResponse(token));

	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
