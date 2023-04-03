package com.propertymanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.propertymanagement.dto.UserDTO;
import com.propertymanagement.service.IUserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	private IUserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
		userDTO = userService.register(userDTO);
		ResponseEntity<UserDTO> responseEntity = new ResponseEntity<>(userDTO, HttpStatus.CREATED);
		return responseEntity;
	}

	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@Valid @RequestBody UserDTO userDTO) {
		userDTO = userService.login(userDTO.getEmail(), userDTO.getPassword());
		ResponseEntity<UserDTO> responseEntity = new ResponseEntity<>(userDTO, HttpStatus.OK);
		return responseEntity;
	}

}
