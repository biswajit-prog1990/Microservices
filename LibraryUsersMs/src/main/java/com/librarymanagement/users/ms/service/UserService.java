package com.librarymanagement.users.ms.service;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.librarymanagement.users.ms.data.IUserRepository;
import com.librarymanagement.users.ms.data.UserEntity;
import com.librarymanagement.users.ms.shared.UserDto;
import com.librarymanagement.users.ms.shared.Util;

@Service
public class UserService implements IUserService {
	Util util;
	IUserRepository iUserRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserService(Util util, IUserRepository iUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.util = util;
		this.iUserRepository = iUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(util.getUserId());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
		iUserRepository.save(userEntity);
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = iUserRepository.findByEmail(username);
		if (userEntity == null)
			throw new UsernameNotFoundException("Username: " + username + " not found");
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true,
				new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity userEntity = iUserRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException("Username: " + email + " not found");
		return new ModelMapper().map(userEntity, UserDto.class);
	}

}
