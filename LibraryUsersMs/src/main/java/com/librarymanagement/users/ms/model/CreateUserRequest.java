package com.librarymanagement.users.ms.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequest {
	@NotNull(message = "First Name cannot be null")
	private String firstName;
	private String lastName;
	@NotNull(message = "Email address is mandatory")
	@Email
	private String email;
	@NotNull(message = "Password is mandatory")
	@Size(min = 8, max = 30, message = "Password should be between 8 and 30 characters")
	private String password;
	@NotNull(message = "User Type needs to be mentioned")
	private String userType;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
