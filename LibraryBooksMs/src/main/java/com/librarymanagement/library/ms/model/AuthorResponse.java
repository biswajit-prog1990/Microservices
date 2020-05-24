package com.librarymanagement.library.ms.model;

import java.util.Date;

import lombok.Getter;

@Getter
public class AuthorResponse {

	private String authorId;
	private String authorName;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
}
