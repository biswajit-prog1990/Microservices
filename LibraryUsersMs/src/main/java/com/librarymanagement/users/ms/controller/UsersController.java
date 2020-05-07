package com.librarymanagement.users.ms.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.users.ms.model.CreateUserRequest;
import com.librarymanagement.users.ms.model.CreateUserResponse;
import com.librarymanagement.users.ms.service.IUserService;
import com.librarymanagement.users.ms.shared.UserDto;

@RestController
@RequestMapping("users")
public class UsersController {
	@Autowired
	private Environment environment;
	@Autowired
	private IUserService iUserService;

	@GetMapping("/status/check")
	public String status() {
		return "Request received from ip address:" + environment.getProperty("local.server.port")
				+ " Application Status is up. Secret Key = " + environment.getProperty("secret.key");
	}

	@PostMapping
	public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto createdUser = iUserService.createUser(userDto);
		CreateUserResponse returnValue = modelMapper.map(createdUser, CreateUserResponse.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
}
