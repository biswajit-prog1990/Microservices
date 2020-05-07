package com.librarymanagement.users.ms.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.librarymanagement.users.ms.shared.UserDto;

public interface IUserService extends UserDetailsService {
	UserDto createUser(UserDto userDetails);

	UserDto getUserDetailsByEmail(String email);
}
