package com.librarymanagement.library.ms.shared;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class Util {

	public String getRandomId() {
		return UUID.randomUUID().toString();
	}

	public String getSystemIpAddress() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
}
