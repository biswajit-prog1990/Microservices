package com.librarymanagement.library.ms.model;

import java.util.Date;

import lombok.Getter;

public class AuthorResponse {

	@Getter
	private String authorId;
	@Getter
	private String authorName;
	@Getter
	private String createdBy;
	@Getter
	private Date createdDate;
	@Getter
	private String updatedBy;
	@Getter
	private Date updatedDate;
}
