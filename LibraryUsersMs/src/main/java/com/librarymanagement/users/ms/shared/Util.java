package com.librarymanagement.users.ms.shared;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class Util {

	public String getUserId() {
		return UUID.randomUUID().toString();
	}
}
